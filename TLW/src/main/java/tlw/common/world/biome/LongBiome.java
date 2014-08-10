package tlw.common.world.biome;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.util.MathHelper;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.biome.BiomeGenBase;
import tlw.common.TLWMod;
import tlw.common.world.LongWorldProperties;

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
	
	@Override
	@SideOnly(Side.CLIENT)
    public int getBiomeGrassColor(int i, int j, int k)
    {
		LongWorldProperties props = TLWMod.proxy.getClientWorldProperties();
        setTemperatureRainfall(props.temperature, props.rainfall);
        return super.getBiomeGrassColor(i, j, k);
    }

	@Override
    @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor(int i, int j, int k)
    {
		LongWorldProperties props = TLWMod.proxy.getClientWorldProperties();
        setTemperatureRainfall(props.temperature, props.rainfall);
        return super.getBiomeFoliageColor(i, j, k);
    }
}
