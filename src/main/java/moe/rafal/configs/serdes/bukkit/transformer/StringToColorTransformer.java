package moe.rafal.configs.serdes.bukkit.transformer;

import eu.okaeri.configs.schema.GenericsPair;
import eu.okaeri.configs.serdes.BidirectionalTransformer;
import eu.okaeri.configs.serdes.SerdesContext;
import org.bukkit.Color;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringToColorTransformer extends BidirectionalTransformer<String, Color> {

    private static final String PALETTE_VALUE_SEPARATOR = " ";

    @Override
    public GenericsPair<String, Color> getPair() {
        return genericsPair(String.class, Color.class);
    }

    @Override
    public Color leftToRight(@NotNull String data, @NotNull SerdesContext serdesContext) {
        int[] palette = Arrays.stream(data.split(PALETTE_VALUE_SEPARATOR))
            .mapToInt(Integer::parseInt)
            .toArray();
        return Color.fromRGB(
            palette[0],
            palette[1],
            palette[2]);
    }

    @Override
    public String rightToLeft(@NotNull Color data, @NotNull SerdesContext serdesContext) {
        return Stream.of(data.getRed(), data.getBlue(), data.getRed())
            .map(String::valueOf)
            .collect(Collectors.joining(PALETTE_VALUE_SEPARATOR));
    }
}
