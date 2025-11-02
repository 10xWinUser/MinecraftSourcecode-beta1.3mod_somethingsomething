package net.minecraft.src;

public class ItemPickaxe extends ItemTool
{
    protected ItemPickaxe(int id, EnumToolMaterial material)
    {
        super(id, 2, material, blocksEffectiveAgainst);
        maxStackSize = 1; // keep pickaxes unstackable
    }

    @Override
    public boolean canHarvestBlock(Block block)
    {
        // Always mine rocks and iron blocks/ores, ignoring tier
        if(block.blockMaterial == Material.rock || block.blockMaterial == Material.iron)
        {
            return true;
        }
        // Optional: bypass all other materials if desired
        return true;
    }

    // THIS is your infinite-speed logic
    @Override
    public float getStrVsBlock(ItemStack itemstack, Block block)
    {
        if(block.blockMaterial == Material.rock || block.blockMaterial == Material.iron)
        {
            return 20F; // insanely fast mining speed
        }
        return super.getStrVsBlock(itemstack, block);
    }

    private static Block blocksEffectiveAgainst[];

    static 
    {
        blocksEffectiveAgainst = new Block[] {
            Block.cobblestone, Block.stairDouble, Block.stairSingle, Block.stone, Block.sandStone,
            Block.cobblestoneMossy, Block.oreIron, Block.blockSteel, Block.oreCoal, Block.blockGold,
            Block.oreGold, Block.oreDiamond, Block.blockDiamond, Block.ice, Block.bloodStone,
            Block.oreLapis, Block.blockLapis
        };
    }
}
