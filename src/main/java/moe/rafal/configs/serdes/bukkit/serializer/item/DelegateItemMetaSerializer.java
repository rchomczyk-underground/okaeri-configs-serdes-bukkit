package moe.rafal.configs.serdes.bukkit.serializer.item;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.SerializationData;
import eu.okaeri.configs.yaml.bukkit.serdes.serializer.ItemMetaSerializer;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

public class DelegateItemMetaSerializer extends ItemMetaSerializer {

    @Override
    public void serialize(@NotNull ItemMeta object, @NotNull SerializationData data, @NotNull GenericsDeclaration generics) {
        super.serialize(object, data, generics);

        if (object.isUnbreakable()) {
            data.add("unbreakable", true);
        }

        if (object instanceof LeatherArmorMeta) {
            data.add("color", ((LeatherArmorMeta) object).getColor());
        }

        if (object.getAttributeModifiers() != null && object.getAttributeModifiers().size() > 0) {
            data.addAsMap("attributes",
                object.getAttributeModifiers().asMap(), GenericsDeclaration.of(
                object.getAttributeModifiers().asMap(), Arrays.asList(Attribute.class, Collection.class)));
        }
    }

    @Override
    public ItemMeta deserialize(@NotNull DeserializationData data, @NotNull GenericsDeclaration generics) {
        ItemMeta itemMeta = super.deserialize(data, generics);

        if (data.containsKey("unbreakable")) {
            itemMeta.setUnbreakable(data.get("unbreakable", boolean.class));
        }

        if (data.containsKey("leather-color")) {
            ItemStack leatherItemStack = new ItemStack(Material.LEATHER_CHESTPLATE);

            LeatherArmorMeta leatherArmorMeta = Objects.requireNonNull((LeatherArmorMeta) leatherItemStack.getItemMeta());
            leatherArmorMeta.setColor(data.get("leather-color", Color.class));

            itemMeta = mergeItemMeta(itemMeta, leatherArmorMeta);
        }

        if (data.containsKey("attributes")) {
            Map<Attribute, Collection<AttributeModifier>> map = data.getAsMap("attributes",
                GenericsDeclaration.of(Map.class, Arrays.asList(Attribute.class,
                GenericsDeclaration.of(ArrayList.class, Collections.singletonList(AttributeModifier.class)))));
            for (Map.Entry<Attribute, Collection<AttributeModifier>> entry : map.entrySet()) {
                Attribute attribute = entry.getKey();
                for (AttributeModifier attributeModifier : entry.getValue()) {
                    itemMeta.addAttributeModifier(attribute, attributeModifier);
                }
            }
        }

        return itemMeta;
    }

    private ItemMeta mergeItemMeta(ItemMeta mergeFrom, ItemMeta mergeInto) {
        if (mergeFrom.hasDisplayName()) {
            mergeInto.setDisplayName(mergeFrom.getDisplayName());
        }

        if (mergeFrom.hasLore()) {
            mergeInto.setLore(mergeFrom.getLore());
        }

        if (mergeFrom.hasEnchants()) {
            mergeFrom.getEnchants().forEach((type, level) -> mergeInto.addEnchant(type, level, true));
        }

        mergeFrom.getItemFlags().forEach(mergeInto::addItemFlags);

        return mergeInto;
    }
}
