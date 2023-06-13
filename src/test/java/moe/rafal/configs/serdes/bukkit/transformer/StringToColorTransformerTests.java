package moe.rafal.configs.serdes.bukkit.transformer;

import org.bukkit.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringToColorTransformerTests {

    private final StringToColorTransformer colorTransformer = new StringToColorTransformer();
    private final String redColorCode = "255 0 0";
    private final Color  redColor = Color.fromRGB(255, 0, 0);

    @Test
    void mutateIntoColorTest() {
        assertEquals(redColor, colorTransformer.mutateIntoColor(redColorCode));
    }

    @Test
    void mutateFromColorTest() {
        assertEquals(redColorCode, colorTransformer.mutateFromColor(redColor));
    }
}
