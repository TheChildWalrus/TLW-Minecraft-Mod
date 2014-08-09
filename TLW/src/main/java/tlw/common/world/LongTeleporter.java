package tlw.common.world;

import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class LongTeleporter extends Teleporter
{
	private WorldServer world;
	
    public LongTeleporter(WorldServer worldserver)
    {
		super(worldserver);
		world = worldserver;
    }

	@Override
    public void placeInPortal(Entity entity, double d, double d1, double d2, float f)
    {
		int i = MathHelper.floor_double(entity.posX);
		int k = MathHelper.floor_double(entity.posZ);
		int j = world.getTopSolidOrLiquidBlock(i, k);
		
		entity.setLocationAndAngles((double)i + 0.5D, (double)j + 1D, (double)k + 0.5D, entity.rotationYaw, 0F);
    }
}
