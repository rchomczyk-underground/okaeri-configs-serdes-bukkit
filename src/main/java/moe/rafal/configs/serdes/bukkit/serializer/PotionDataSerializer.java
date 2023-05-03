package moe.rafal.configs.serdes.bukkit.serializer;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;
import org.jetbrains.annotations.NotNull;

public class PotionDataSerializer implements ObjectSerializer<PotionData> {

    @Override
    public boolean supports(@NotNull Class<? super PotionData> type) {
        return PotionData.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NotNull PotionData object, @NotNull SerializationData data, @NotNull GenericsDeclaration generics) {
        data.add("type", object.getType());
        data.add("extended", object.isExtended());
        data.add("upgraded", object.isUpgraded());
    }

    @Override
    public PotionData deserialize(@NotNull DeserializationData data, @NotNull GenericsDeclaration generics) {
        return new PotionData(
            data.get("type", PotionType.class),
            data.get("extended", boolean.class),
            data.get("upgraded", boolean.class));
    }
}
