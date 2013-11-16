package sionois.terraduellum.Render;

import org.lwjgl.opengl.GL11;

import sionois.terraduellum.Entities.EntityPlayerGhost;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class RenderPlayerGhost extends RenderBiped
{
	private static final ResourceLocation field_110826_a = new ResourceLocation("textures/entity/steve.png");
    private ModelBiped modelBipedMain;
    private ModelBiped modelArmorChestplate;
    private ModelBiped modelArmor;
    
    public RenderPlayerGhost()
    {
        super(new ModelBiped(0.0F), 0.5F);
        this.modelBipedMain = (ModelBiped)this.mainModel;
        this.modelArmorChestplate = new ModelBiped(1.0F);
        this.modelArmor = new ModelBiped(0.5F);
    }
    protected ResourceLocation func_110863_a(EntityPlayerGhost par1EntityZombie)
    {
        return field_110826_a;
    }
    protected ResourceLocation func_110856_a(EntityLiving par1EntityLiving)
    {
        return this.func_110863_a((EntityPlayerGhost)par1EntityLiving);
    }
    protected ResourceLocation func_110775_a(Entity par1Entity)
    {
        return this.func_110863_a((EntityPlayerGhost)par1Entity);
    }
    protected int func_82429_a(EntityPlayerGhost par1EntityPlayerGhost, int par2, float par3)
    {
        return super.shouldRenderPass(par1EntityPlayerGhost, par2, par3);
    }

    public void func_82426_a(EntityPlayerGhost par1EntityPlayerGhost, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRenderLiving(par1EntityPlayerGhost, par2, par4, par6, par8, par9);
    }

    protected void func_82428_a(EntityPlayerGhost par1EntityPlayerGhost, float par2)
    {
        super.renderEquippedItems(par1EntityPlayerGhost, par2);
    }
    protected void func_82430_a(EntityPlayerGhost par1EntityPlayerGhost, float par2, float par3, float par4)
    {
        super.rotateCorpse(par1EntityPlayerGhost, par2, par3, par4);
    }

    protected void renderEquippedItems(EntityLivingBase par1EntityLivingBase, float par2)
    {
        this.func_82428_a((EntityPlayerGhost)par1EntityLivingBase, par2);
    }

    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.func_82426_a((EntityPlayerGhost)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
    {
        return this.func_82429_a((EntityPlayerGhost)par1EntityLivingBase, par2, par3);
    }

    protected void rotateCorpse(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4)
    {
        this.func_82430_a((EntityPlayerGhost)par1EntityLivingBase, par2, par3, par4);
    }
    
    public void renderPlayer(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9)
    {
        this.func_82426_a((EntityPlayerGhost)par1EntityLivingBase, par2, par4, par6, par8, par9);
    }
    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.func_82426_a((EntityPlayerGhost)par1Entity, par2, par4, par6, par8, par9);
    }
}