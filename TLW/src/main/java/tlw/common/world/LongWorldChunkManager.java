package tlw.common.world;

import net.minecraft.world.biome.WorldChunkManagerHell;
import tlw.common.world.biome.LongBiome;

public class LongWorldChunkManager extends WorldChunkManagerHell
{
    public LongWorldChunkManager()
    {
		super(LongBiome.longBiome, LongBiome.longBiome.getFloatRainfall());
    }
}
