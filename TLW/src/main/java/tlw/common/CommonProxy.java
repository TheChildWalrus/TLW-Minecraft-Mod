package tlw.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler
{
	public void onPreload() {}
	
	public void onLoad() {}
	
	public void onPostload() {}

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
