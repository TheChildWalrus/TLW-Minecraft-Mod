package tlw.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.*;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;

@Sharable
public class PacketHandlerServer extends SimpleChannelInboundHandler<FMLProxyPacket>
{
	public PacketHandlerServer()
	{
		//NetworkRegistry.INSTANCE.newChannel("tlw.thing", this);
	}
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, FMLProxyPacket packet) throws Exception
	{
		ByteBuf data = packet.payload();
		String channel = packet.channel();
	}
}
