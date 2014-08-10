package tlw.common;

import tlw.common.world.LongWorldProperties;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler
{
	public void onPreload() {}
	
	public void onLoad() {}
	
	public void onPostload() {}
	
	public LongWorldProperties getClientWorldProperties()
	{
		return null;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer entityplayer, World world, int i, int j, int k)
	{
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer entityplayer, World world, int i, int j, int k)
	{
		return null;
	}
}
