package tlw.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.*;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;

@Sharable
public class PacketHandlerClient extends SimpleChannelInboundHandler<FMLProxyPacket>
{
	public PacketHandlerClient()
	{
		//NetworkRegistry.INSTANCE.newChannel("tlw.thing", this);
	}
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, FMLProxyPacket packet) throws Exception
	{
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer entityplayer = mc.thePlayer;
		World world = entityplayer.worldObj;
		
		ByteBuf data = packet.payload();
		String channel = packet.channel();
	}
}
