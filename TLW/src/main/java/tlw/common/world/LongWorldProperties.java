package tlw.common.world;

import java.util.*;

import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.*;

public class LongWorldProperties
{
	private static Random rand = new Random();
	
	private static Map<Integer, LongWorldProperties> props = new HashMap();
	
	public static LongWorldProperties getLongProperties(World world, int ID)
	{
		LongWorldProperties lwp = props.get(ID);
		if (lwp == null)
		{
			lwp = new LongWorldProperties();
			lwp.generate(world.getSeed(), ID);
			props.put(ID, lwp);
		}
		return lwp;
	}
	
	public float temperature;
	public float rainfall;
	
	public float baseHeight;
	public float heightVariation;
	
	public float lakeChance;
	public float lavaLakeChance;
	
	public float animalChance;
	
	public int cobwebsPerChunk;
	public int clayPerChunk;
	public int sandPerChunk;
	
	public int treesPerChunk;
	public float treeIncreaseChance;
	
	public int logsPerChunk;
	public int vinesPerChunk;
	public int flowersPerChunk;
	public int doubleFlowersPerChunk;
	public int grassPerChunk;
	public int doubleGrassPerChunk;
	public boolean enableFern;
	public int lilyPerChunk;
	public int reedsPerChunk;
	public int waterFlowPerChunk;
	public int lavaFlowPerChunk;
	
	public void generate(long seed, int ID)
	{
		rand.setSeed(seed);
        long l = (rand.nextLong() / 2L) * 2L + 1L;
        rand.setSeed((long)ID * l ^ seed);
        
        int i;
        
        temperature = rand.nextFloat() * 1.2F;
        rainfall = rand.nextFloat() * 1.2F;
        
        baseHeight = 0F + rand.nextFloat() * 1.5F;
        heightVariation = 0F + rand.nextFloat() * 1.5F;
        
        lakeChance = rand.nextFloat() * 1F;
        lavaLakeChance = rand.nextFloat() * 0.3F;
        
        animalChance = rand.nextFloat() * rand.nextFloat() * rand.nextFloat() * 2F;
        
        cobwebsPerChunk = rand.nextInt(4);
        clayPerChunk = rand.nextInt(6);
        sandPerChunk = rand.nextInt(6);
        
        i = rand.nextInt(3);
        if (i == 0)
        {
        	treesPerChunk = MathHelper.getRandomIntegerInRange(rand, 0, 1);
        }
        else if (i == 1)
        {
        	treesPerChunk = MathHelper.getRandomIntegerInRange(rand, 2, 4);
        }
        else if (i == 2)
        {
        	treesPerChunk = MathHelper.getRandomIntegerInRange(rand, 5, 12);
        }
        treeIncreaseChance = rand.nextFloat();
        
        logsPerChunk = logsPerChunk * rand.nextInt(3) + rand.nextInt(3);
        vinesPerChunk = treesPerChunk * rand.nextInt(4);
        flowersPerChunk = rand.nextInt(10);
        doubleFlowersPerChunk = flowersPerChunk * rand.nextInt(3) + rand.nextInt(3);
        grassPerChunk = rand.nextInt(10);
        doubleGrassPerChunk = grassPerChunk * rand.nextInt(3) + rand.nextInt(3);
        enableFern = rand.nextBoolean();
        lilyPerChunk = rand.nextInt(4);
        reedsPerChunk = rand.nextInt(20);
        waterFlowPerChunk = rand.nextInt(100);
        lavaFlowPerChunk = Math.round((100 - waterFlowPerChunk) * rand.nextFloat());
	}
	
	public WorldGenerator getRandomTree()
	{
		return new WorldGenTrees(false);
	}
	
	public WorldGenerator getRandomGrass()
	{
		if (enableFern && rand.nextInt(4) == 0)
		{
			return new WorldGenTallGrass(Blocks.tallgrass, 2);
		}
		return new WorldGenTallGrass(Blocks.tallgrass, 1);
	}
	
	public WorldGenerator getRandomDoubleGrass()
	{
		WorldGenDoublePlant generator = new WorldGenDoublePlant();
		if (enableFern && rand.nextInt(4) == 0)
		{
			generator.func_150548_a(3);
		}
		else
		{
			generator.func_150548_a(2);
		}
		return generator;
	}
	
	public WorldGenerator getRandomDoubleFlower()
	{
        WorldGenDoublePlant doubleFlowerGen = new WorldGenDoublePlant();
        int i = rand.nextInt(3);
        switch (i)
        {
        	case 0:
        		doubleFlowerGen.func_150548_a(1);
        		break;
        	case 1:
        		doubleFlowerGen.func_150548_a(4);
        		break;
        	case 2:
        		doubleFlowerGen.func_150548_a(5);
        		break;
        }
        return doubleFlowerGen;
	}
}
