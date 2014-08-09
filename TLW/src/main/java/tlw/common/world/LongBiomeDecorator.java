package tlw.common.world;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.*;
import tlw.common.world.feature.*;

public class LongBiomeDecorator
{
    private static World worldObj;
    private static Random rand;
    private static int chunkX;
    private static int chunkZ;
    
    private static LongWorldProperties worldProps;

    private static WorldGenerator clayGen = new WorldGenClay(4);
    private static WorldGenerator sandGen = new WorldGenSand(Blocks.sand, 7);
	
    private static WorldGenerator dirtGen = new WorldGenMinable(Blocks.dirt, 32);
    private static WorldGenerator gravelGen = new WorldGenMinable(Blocks.gravel, 32);
    private static WorldGenerator coalGen = new WorldGenMinable(Blocks.coal_ore, 16);
    private static WorldGenerator ironGen = new WorldGenMinable(Blocks.iron_ore, 8);
    private static WorldGenerator goldGen = new WorldGenMinable(Blocks.gold_ore, 8);
    private static WorldGenerator diamondGen = new WorldGenMinable(Blocks.diamond_ore, 7);
	private static WorldGenerator lapisGen = new WorldGenMinable(Blocks.lapis_ore, 6);
	
	private static WorldGenerator flowerGen = new WorldGenBiomeFlowers();
	private static WorldGenerator logGen = new WorldGenLogs();
    private static WorldGenerator mushroomBrownGen = new WorldGenFlowers(Blocks.brown_mushroom);
    private static WorldGenerator mushroomRedGen = new WorldGenFlowers(Blocks.red_mushroom);
    private static WorldGenerator reedGen = new WorldGenReed();
	private static WorldGenerator pumpkinGen = new WorldGenPumpkin();
    private static WorldGenerator waterlilyGen = new WorldGenWaterlily();
	private static WorldGenerator cobwebGen = new WorldGenCaveCobwebs();
	private static WorldGenerator vinesGen = new WorldGenVines();
	private static WorldGenerator cactusGen = new WorldGenCactus();

    public static void decorate(LongWorldProperties worldproperties, World world, Random random, int i, int j)
    {
		worldObj = world;
		rand = random;
		chunkX = i;
		chunkZ = j;
		decorate();
    }

    private static void decorate()
    {
        generateOres();
		
		for (int l = 0; l < worldProps.cobwebsPerChunk; l++)
		{
            int i = chunkX + rand.nextInt(16) + 8;
            int j = rand.nextInt(128);
            int k = chunkZ + rand.nextInt(16) + 8;
            cobwebGen.generate(worldObj, rand, i, j, k);
		}

        for (int l = 0; l < worldProps.clayPerChunk; l++)
        {
            int i = chunkX + rand.nextInt(16) + 8;
            int k = chunkZ + rand.nextInt(16) + 8;
            clayGen.generate(worldObj, rand, i, worldObj.getTopSolidOrLiquidBlock(i, k), k);
        }

        for (int l = 0; l < worldProps.sandPerChunk; l++)
        {
            int i = chunkX + rand.nextInt(16) + 8;
            int k = chunkZ + rand.nextInt(16) + 8;
            sandGen.generate(worldObj, rand, i, worldObj.getTopSolidOrLiquidBlock(i, k), k);
        }

        int trees = worldProps.treesPerChunk;

        if (rand.nextFloat() < worldProps.treeIncreaseChance)
        {
            trees++;
        }

        for (int l = 0; l < trees; l++)
        {
            int i = chunkX + rand.nextInt(16) + 8;
            int k = chunkZ + rand.nextInt(16) + 8;
            WorldGenerator treeGen = worldProps.getRandomTree();
            treeGen.generate(worldObj, rand, i, worldObj.getHeightValue(i, k), k);
        }
        
        for (int l = 0; l < worldProps.logsPerChunk; l++)
        {
            int i = chunkX + rand.nextInt(16) + 8;
            int k = chunkZ + rand.nextInt(16) + 8;
            logGen.generate(worldObj, rand, i, worldObj.getHeightValue(i, k), k);
        }
		
        for (int l = 0; l < worldProps.vinesPerChunk; l++)
        {
            int i = chunkX + rand.nextInt(16) + 8;
			int j = 64;
            int k = chunkZ + rand.nextInt(16) + 8;
            vinesGen.generate(worldObj, rand, i, j, k);
        }

        for (int l = 0; l < worldProps.flowersPerChunk; l++)
        {
            int i = chunkX + rand.nextInt(16) + 8;
            int j = rand.nextInt(128);
            int k = chunkZ + rand.nextInt(16) + 8;
            flowerGen.generate(worldObj, rand, i, j, k);
        }
        
        for (int l = 0; l < worldProps.doubleFlowersPerChunk; l++)
        {
            int i = chunkX + rand.nextInt(16) + 8;
            int j = rand.nextInt(128);
            int k = chunkZ + rand.nextInt(16) + 8;
            WorldGenerator doubleFlowerGen = worldProps.getRandomDoubleFlower();
            doubleFlowerGen.generate(worldObj, rand, i, j, k);
        }

        for (int l = 0; l < worldProps.grassPerChunk; l++)
        {
            int i = chunkX + rand.nextInt(16) + 8;
            int j = rand.nextInt(128);
            int k = chunkZ + rand.nextInt(16) + 8;
            WorldGenerator grassGen = worldProps.getRandomGrass();
            grassGen.generate(worldObj, rand, i, j, k);
        }
        
        for (int l = 0; l < worldProps.doubleGrassPerChunk; l++)
        {
            int i = chunkX + rand.nextInt(16) + 8;
            int j = rand.nextInt(128);
            int k = chunkZ + rand.nextInt(16) + 8;
            WorldGenerator grassGen = worldProps.getRandomDoubleGrass();
            grassGen.generate(worldObj, rand, i, j, k);
        }

        for (int l = 0; l < worldProps.lilyPerChunk; l++)
        {
            int i = chunkX + rand.nextInt(16) + 8;
            int k = chunkZ + rand.nextInt(16) + 8;
			int j;
			
            for (j = rand.nextInt(128); j > 0 && worldObj.getBlock(i, j - 1, k) == Blocks.air; j--)
            {
                ;
            }

            waterlilyGen.generate(worldObj, rand, i, j, k);
        }

        for (int l = 0; l < 2; l++)
        {
            if (rand.nextInt(4) == 0)
            {
                int i = chunkX + rand.nextInt(16) + 8;
                int k = chunkZ + rand.nextInt(16) + 8;
                int j = worldObj.getHeightValue(i, k);
                mushroomBrownGen.generate(worldObj, rand, i, j, k);
            }

            if (rand.nextInt(8) == 0)
            {
                int i = chunkX + rand.nextInt(16) + 8;
                int k = chunkZ + rand.nextInt(16) + 8;
                int j = worldObj.getHeightValue(i, k);
                mushroomRedGen.generate(worldObj, rand, i, j, k);
            }
        }

        if (rand.nextInt(4) == 0)
        {
            int i = chunkX + rand.nextInt(16) + 8;
            int j = rand.nextInt(128);
            int k = chunkZ + rand.nextInt(16) + 8;
            mushroomBrownGen.generate(worldObj, rand, i, j, k);
        }

        if (rand.nextInt(8) == 0)
        {
            int i = chunkX + rand.nextInt(16) + 8;
            int j = rand.nextInt(128);
            int k = chunkZ + rand.nextInt(16) + 8;
            mushroomRedGen.generate(worldObj, rand, i, j, k);
        }

        for (int l = 0; l < worldProps.reedsPerChunk; l++)
        {
            int i = chunkX + rand.nextInt(16) + 8;
			int j = rand.nextInt(128);
            int k = chunkZ + rand.nextInt(16) + 8;
            reedGen.generate(worldObj, rand, i, j, k);
        }
		
        for (int l = 0; l < 0; l++)
        {
            int i = chunkX + rand.nextInt(16) + 8;
			int j = rand.nextInt(128);
            int k = chunkZ + rand.nextInt(16) + 8;
            cactusGen.generate(worldObj, rand, i, j, k);
        }
		
        if (worldProps.flowersPerChunk > 0 && rand.nextInt(32) == 0)
        {
            int i = chunkX + rand.nextInt(16) + 8;
			int j = rand.nextInt(128);
            int k = chunkZ + rand.nextInt(16) + 8;
            pumpkinGen.generate(worldObj, rand, i, j, k);
        }

		WorldGenerator waterGen = new WorldGenLiquids(Blocks.flowing_water);
        for (int l = 0; l < worldProps.waterFlowPerChunk; l++)
        {
            int i = chunkX + rand.nextInt(16) + 8;
            int j = rand.nextInt(rand.nextInt(120) + 8);
            int k = chunkZ + rand.nextInt(16) + 8;
            waterGen.generate(worldObj, rand, i, j, k);
        }
		
        WorldGenerator lavaGen = new WorldGenLiquids(Blocks.flowing_lava);
		for (int l = 0; l < worldProps.lavaFlowPerChunk; l++)
		{
			int i = chunkX + rand.nextInt(16) + 8;
			int j = rand.nextInt(rand.nextInt(rand.nextInt(112) + 8) + 8);
			int k = chunkZ + rand.nextInt(16) + 8;
			lavaGen.generate(worldObj, rand, i, j, k);
		}
    }

    private static void genStandardOre(int ores, WorldGenerator oreGen, int offset, int heightRange)
    {
        for (int l = 0; l < ores; l++)
        {
            int i = chunkX + rand.nextInt(16);
            int j = rand.nextInt(heightRange - offset) + offset;
            int k = chunkZ + rand.nextInt(16);
            oreGen.generate(worldObj, rand, i, j, k);
        }
    }

    private static void genErraticOre(int ores, WorldGenerator oreGen, int offset, int heightRange)
    {
        for (int l = 0; l < ores; l++)
        {
            int i = chunkX + rand.nextInt(16);
            int j = rand.nextInt(heightRange) + rand.nextInt(heightRange) + (offset - heightRange);
            int k = chunkZ + rand.nextInt(16);
            oreGen.generate(worldObj, rand, i, j, k);
        }
    }

    private static void generateOres()
    {
    	int passes = 1;
    	
    	for (int l = 0; l < passes; l++)
	    {
	        genStandardOre(20, dirtGen, 0, 128);
	        genStandardOre(10, gravelGen, 0, 128);
			
	        genStandardOre(20, coalGen, 0, 128);
	        genStandardOre(20, ironGen, 0, 64);
	        genStandardOre(2, goldGen, 0, 32);
	        genStandardOre(1, diamondGen, 0, 16);
			genErraticOre(1, lapisGen, 16, 16);
		
			if (worldProps.baseHeight > 1F)
			{
				genStandardOre(20, dirtGen, 128, 256);
				genStandardOre(10, gravelGen, 128, 256);
				
				genStandardOre(10, coalGen, 128, 256);
				genStandardOre(10, ironGen, 64, 128);
			}
	    }
    }
}
