package net.minecraft.src;

public class ItemTool extends Item
{
    protected ItemTool(int id, int baseDamage, EnumToolMaterial material, Block[] effectiveBlocks)
    {
        super(id);
        efficiencyOnProperMaterial = 4F;
        toolMaterial = material;
        blocksEffectiveAgainst = effectiveBlocks;
        maxStackSize = 1;
        maxDamage = material.getMaxUses(); // original durability, unused since we override hit methods
        efficiencyOnProperMaterial = material.getEfficiencyOnProperMaterial();
        damageVsEntity = baseDamage + material.getDamageVsEntity();
    }

    @Override
    public float getStrVsBlock(ItemStack itemstack, Block block)
    {
        // Infinite speed logic for pickaxes, shovels, axes
        if(this instanceof ItemPickaxe)
        {
            if(block.blockMaterial == Material.rock || block.blockMaterial == Material.iron)
            {
                return 20F; // super fast mining
            }
        }

        if(this instanceof ItemSpade)
        {
            if(block.blockMaterial == Material.ground || block.blockMaterial == Material.sand || block.blockMaterial == Material.snow)
            {
                return 20F;
            }
        }

        if(this instanceof ItemAxe)
        {
            if(block.blockMaterial == Material.wood)
            {
                return 20F;
            }
        }

        // Default Beta 1.3_01 behavior
        for(int i = 0; i < blocksEffectiveAgainst.length; i++)
        {
            if(blocksEffectiveAgainst[i] == block)
            {
                return efficiencyOnProperMaterial;
            }
        }

        return 1.0F;
    }

    @Override
    public void hitEntity(ItemStack itemstack, EntityLiving entityliving)
    {
        // Infinite durability: do not reduce item damage
    }

    @Override
    public void hitBlock(ItemStack itemstack, int i, int j, int k, int l)
    {
        // Infinite durability: do not reduce item damage
    }

    @Override
    public int getDamageVsEntity(Entity entity)
    {
        return damageVsEntity;
    }

    @Override
    public boolean isFull3D()
    {
        return true;
    }

    private Block[] blocksEffectiveAgainst;
    private float efficiencyOnProperMaterial;
    private int damageVsEntity;
    protected EnumToolMaterial toolMaterial;
}
