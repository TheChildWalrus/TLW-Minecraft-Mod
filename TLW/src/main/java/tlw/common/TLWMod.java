package tlw.common;

import java.lang.reflect.Field;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import tlw.common.item.ItemStepper;
import cpw.mods.fml.common.*;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = "tlw", name = "The Long Worlds", version = "1.0")
public class TLWMod
{
	@SidedProxy(clientSide = "tlw.client.ClientProxy", serverSide = "tlw.common.CommonProxy")
	public static CommonProxy proxy;
	
	@Mod.Instance("tlw")
	public static TLWMod instance;
	
	public static CreativeTabs creativeTab = new CreativeTabs("tlw")
    {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem()
        {
            return stepper;
        }
    };
	
	public static Item stepper;
	
	public static int[] dimensionIDs;
	public static int biomeID;
	
	@Mod.EventHandler
	public void preload(FMLPreInitializationEvent event)
	{
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		
		stepper = new ItemStepper().setUnlocalizedName("tlw:stepper");

		try
		{
			String prefix = getModID() + ":";
			for (Field field : TLWMod.class.getFields())
			{
				if (field.get(null) instanceof Block)
				{
					Block block = (Block)field.get(null);
					String newName = block.getUnlocalizedName().substring(prefix.length() + 1);
					field.set(null, block.setBlockTextureName(newName));
				}
				
				if (field.get(null) instanceof Item)
				{
					Item item = (Item)field.get(null);
					String newName = item.getUnlocalizedName().substring(prefix.length() + 1);
					field.set(null, item.setTextureName(newName));
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		registerItem(stepper);
		
		dimensionIDs = config.get("general", "Dimension IDs", new int[] {50, 51, 52, 53, 54, 55, 56, 57, 58, 59}).getIntList();
		biomeID = config.get("general", "Biome ID", 200).getInt();

		if (config.hasChanged())
		{
			config.save();
		}
		
		proxy.onPreload();
	}
	
	@Mod.EventHandler
	public void load(FMLInitializationEvent event)
	{
		proxy.onLoad();
		
		Recipes.createRecipes();

		NetworkRegistry.INSTANCE.registerGuiHandler(this, proxy);
		
		FMLEventChannel channel = NetworkRegistry.INSTANCE.newEventDrivenChannel("tlw");
		channel.register(new PacketHandlerServer());
	}
	
	@Mod.EventHandler
	public void postload(FMLPostInitializationEvent event)
	{
		proxy.onPostload();
	}
	
	private void registerBlock(Block block)
	{
		GameRegistry.registerBlock(block, block.getUnlocalizedName());
	}
	
	private void registerBlock(Block block, Class itemClass)
	{
		GameRegistry.registerBlock(block, itemClass, block.getUnlocalizedName());
	}
	
	private void registerItem(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName());
	}
	
	public static String getModID()
	{
		ModContainer container = FMLCommonHandler.instance().findContainerFor(instance);
		return container.getModId();
	}
}

	
