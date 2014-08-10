package tlw.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import tlw.common.TLWMod;
import tlw.common.world.LongTeleporter;
import tlw.common.world.LongWorldProvider;

public class ItemStepper extends Item
{
	public static int stepID = 0;
	
	public ItemStepper()
	{
		super();
		setMaxStackSize(1);
		setCreativeTab(TLWMod.creativeTab);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
		if (!world.isRemote)
		{
			stepID++;

			int dim = TLWMod.dimensionIDs[0];
			
			if (DimensionManager.isDimensionRegistered(dim))
			{
				DimensionManager.unregisterProviderType(dim);
				DimensionManager.unregisterDimension(dim);
			}
			DimensionManager.registerProviderType(dim, LongWorldProvider.class, true);
			DimensionManager.registerDimension(dim, dim);
			DimensionManager.initDimension(dim);
			
			WorldServer newWorld = DimensionManager.getWorld(dim);
			MinecraftServer.getServer().getConfigurationManager().transferPlayerToDimension((EntityPlayerMP)entityplayer, dim, new LongTeleporter(newWorld));
		}
		
        return itemstack;
    }
}
