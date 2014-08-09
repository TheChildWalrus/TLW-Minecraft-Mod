package tlw.common;

import static tlw.common.TLWMod.stepper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes
{
	public static void createRecipes()
	{
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(stepper), new Object[]
		{
			Items.gold_ingot, Items.redstone, Items.potato
		}));
	}
}
