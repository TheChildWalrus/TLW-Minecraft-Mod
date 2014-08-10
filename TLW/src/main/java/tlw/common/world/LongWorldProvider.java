package tlw.common.world;

import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import tlw.common.TLWMod;
import tlw.common.item.ItemStepper;
import tlw.common.world.biome.LongBiome;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LongWorldProvider extends WorldProvider 
{
	public int stepID;
	
	public LongWorldProvider()
	{
		stepID = ItemStepper.stepID;
	}
	
	@Override
    public void registerWorldChunkManager()
    {
        worldChunkMgr = new LongWorldChunkManager(worldObj, stepID);
        dimensionId = TLWMod.dimensionIDs[0];
    }
    
	@Override
    public IChunkProvider createChunkGenerator()
    {
        return new LongChunkProvider(this, worldObj, worldObj.getSeed());
    }

	@Override
    public boolean canRespawnHere()
    {
        return true;
    }
	
	@Override
    public String getWelcomeMessage()
	{
		return "Stepping";
	}
	
	@Override
    public String getDepartMessage()
	{
		return "Stepping";
	}
	
	@Override
    public String getSaveFolder()
	{
		return "TLW/" + stepID;
	}
	
	@Override
    public String getDimensionName()
    {
        return "MiddleEarth";
    }
	
	/*@Override
    public ChunkCoordinates getSpawnPoint()
    {
        return new ChunkCoordinates(LOTRLevelData.middleEarthPortalX, LOTRLevelData.middleEarthPortalY, LOTRLevelData.middleEarthPortalZ);
    }*/

	/*@Override
    public void setSpawnPoint(int i, int j, int k)
    {
		if (!(i == 8 && j == 64 && k == 8) && !worldObj.isRemote)
		{
			LOTRLevelData.markMiddleEarthPortalLocation(i, j, k);
		}
    }*/
	
	@Override
	public boolean shouldMapSpin(String entity, double x, double y, double z)
    {
        return false;
    }
	
	@Override
	protected void generateLightBrightnessTable()
    {
        float f = 0F;

        for (int i = 0; i <= 15; ++i)
        {
            float f1 = 1F - (float)i / 15F;
            lightBrightnessTable[i] = (1F - f1) / (f1 * 3F + 1F) * (1F - f) + f;
        }
    }
	
	@Override
	public float calculateCelestialAngle(long time, float partialTicks)
	{
		return super.calculateCelestialAngle(time, partialTicks);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public float getCloudHeight()
    {
        return super.getCloudHeight() + Math.max(0F, LongWorldProperties.getLongProperties(worldObj, stepID).baseHeight * 60F);
    }
       
	@Override
    @SideOnly(Side.CLIENT)
    public Vec3 drawClouds(float partialTicks)
    {
		return super.drawClouds(partialTicks);
    }
	
	@Override
    @SideOnly(Side.CLIENT)
    public Vec3 getFogColor(float f, float f1)
    {
		return super.getFogColor(f, f1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean doesXZShowFog(int i, int k)
    {
		return super.doesXZShowFog(i, k);
    }
}
