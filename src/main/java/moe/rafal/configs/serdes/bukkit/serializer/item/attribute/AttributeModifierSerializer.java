package moe.rafal.configs.serdes.bukkit.serializer.item.attribute;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;

public class AttributeModifierSerializer implements ObjectSerializer<AttributeModifier> {

    @Override
    public boolean supports(@NotNull Class<? super AttributeModifier> type) {
        return AttributeModifier.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NotNull AttributeModifier object, @NotNull SerializationData data, @NotNull GenericsDeclaration generics) {
        data.add("uuid", object.getUniqueId());
        data.add("name", object.getName());
        data.add("amount", object.getAmount());
        data.add("operation", object.getOperation());
        data.add("equipment", object.getSlot());
    }

    @Override
    public AttributeModifier deserialize(@NotNull DeserializationData data, @NotNull GenericsDeclaration generics) {
        return new AttributeModifier(Optional.ofNullable(
            data.get("uuid", UUID.class)).orElseGet(UUID::randomUUID),
            data.get("name", String.class),
            data.get("amount", double.class),
            data.get("operation", AttributeModifier.Operation.class),
            data.get("equipment", EquipmentSlot.class));
    }
}