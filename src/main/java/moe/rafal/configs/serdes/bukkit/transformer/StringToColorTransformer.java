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
        return mutateIntoColor(data);
    }

    @Override
    public String rightToLeft(@NotNull Color data, @NotNull SerdesContext serdesContext) {
        return mutateFromColor(data);
    }

    Color mutateIntoColor(@NotNull String value) {
        int[] components = Arrays.stream(value.split(PALETTE_VALUE_SEPARATOR))
            .mapToInt(Integer::parseInt)
            .toArray();
        return Color.fromRGB(
            components[0],
            components[1],
            components[2]);
    }

    String mutateFromColor(@NotNull Color value) {
        return Stream.of(value.getRed(), value.getGreen(), value.getBlue())
            .map(String::valueOf)
            .collect(Collectors.joining(PALETTE_VALUE_SEPARATOR));
    }
}
