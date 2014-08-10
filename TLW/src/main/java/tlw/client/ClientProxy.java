package tlw.client;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import tlw.common.CommonProxy;
import tlw.common.TLWMod;
import tlw.common.world.LongWorldProperties;
import tlw.common.world.LongWorldProvider;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.NetworkRegistry;

public class ClientProxy extends CommonProxy
{
	//public static TickHandlerClient tickHandler = new TickHandlerClient();
	
	@Override
	public void onLoad()
	{
		FMLEventChannel channel = NetworkRegistry.INSTANCE.newEventDrivenChannel(TLWMod.getModID());
		channel.register(new PacketHandlerClient());
	}
	
	@Override
	public LongWorldProperties getClientWorldProperties()
	{
		Minecraft mc = Minecraft.getMinecraft();
		World world = mc.theWorld;
		return LongWorldProperties.getLongProperties(world, ((LongWorldProvider)world.provider).stepID);
	}
}