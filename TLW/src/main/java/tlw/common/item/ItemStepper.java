package tlw.common.item;

import tlw.common.TLWMod;
import net.minecraft.item.Item;

public class ItemStepper extends Item
{
	public ItemStepper()
	{
		super();
		setMaxStackSize(1);
		setCreativeTab(TLWMod.creativeTab);
	}
}
