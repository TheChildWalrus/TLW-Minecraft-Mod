package tlw.client;

import tlw.common.CommonProxy;
import tlw.common.TLWMod;
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
}