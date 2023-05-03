package moe.rafal.configs.serdes.bukkit;

import eu.okaeri.configs.serdes.SerdesRegistry;
import moe.rafal.configs.serdes.bukkit.serializer.PotionDataSerializer;
import moe.rafal.configs.serdes.bukkit.serializer.item.DelegateItemMetaSerializer;
import moe.rafal.configs.serdes.bukkit.serializer.item.attribute.AttributeModifierSerializer;
import moe.rafal.configs.serdes.bukkit.transformer.StringToColorTransformer;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class SerdesBukkit extends eu.okaeri.configs.yaml.bukkit.serdes.SerdesBukkit {

    @Override
    public void register(@NotNull SerdesRegistry registry) {
        super.register(registry);
        registry.register(new StringToColorTransformer());
        registry.registerExclusive(ItemMeta.class, new DelegateItemMetaSerializer());
        registry.register(new AttributeModifierSerializer());
        registry.register(new PotionDataSerializer());
    }
}
