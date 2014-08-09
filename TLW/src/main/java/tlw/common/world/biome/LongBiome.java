package tlw.common.world.biome;

import java.util.Random;

import net.minecraft.util.WeightedRandom;
import net.minecraft.world.biome.BiomeGenBase;
import tlw.common.TLWMod;

public class LongBiome extends BiomeGenBase
{
	public static LongBiome longBiome = new LongBiome(TLWMod.biomeID);

	public LongBiome(int i)
	{
		super(i);
		
		setBiomeName("Long Biome");
		
		spawnableMonsterList.clear();
	}
	
	public FlowerEntry getRandomFlower(Random random)
	{
		return (FlowerEntry)WeightedRandom.getRandomItem(random, flowers);
	}
}
