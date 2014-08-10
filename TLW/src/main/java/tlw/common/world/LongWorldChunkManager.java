package tlw.common.world;

import net.minecraft.world.World;
import net.minecraft.world.biome.WorldChunkManagerHell;
import tlw.common.world.biome.LongBiome;

public class LongWorldChunkManager extends WorldChunkManagerHell
{
    public LongWorldChunkManager(World world, int stepID)
    {
		super(LongBiome.longBiome, LongWorldProperties.getLongProperties(world, stepID).rainfall);
    }
}
