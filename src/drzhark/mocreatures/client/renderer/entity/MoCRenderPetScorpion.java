package drzhark.mocreatures.client.renderer.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.client.MoCClientProxy;
import drzhark.mocreatures.client.model.MoCModelScorpion;
import drzhark.mocreatures.entity.passive.MoCEntityPetScorpion;

@SideOnly(Side.CLIENT)
public class MoCRenderPetScorpion extends MoCRenderMoC {

    private static final ResourceLocation TEXTURE_DEFAULT = new ResourceLocation("mocreatures", MoCreatures.proxy.MODEL_TEXTURE + "scorpiondirt.png");
    private static final ResourceLocation TEXTURE_DIRTSADDLE = new ResourceLocation("mocreatures", MoCreatures.proxy.MODEL_TEXTURE + "scorpiondirtsaddle.png");
    private static final ResourceLocation TEXTURE_CAVE = new ResourceLocation("mocreatures", MoCreatures.proxy.MODEL_TEXTURE + "scorpioncave.png");
    private static final ResourceLocation TEXTURE_CAVESADDLE = new ResourceLocation("mocreatures", MoCreatures.proxy.MODEL_TEXTURE + "scorpioncavesaddle.png");
    private static final ResourceLocation TEXTURE_NETHER = new ResourceLocation("mocreatures", MoCreatures.proxy.MODEL_TEXTURE + "scorpionnether.png");
    private static final ResourceLocation TEXTURE_NETHERSADDLE = new ResourceLocation("mocreatures", MoCreatures.proxy.MODEL_TEXTURE + "scorpionnethersaddle.png");
    private static final ResourceLocation TEXTURE_FROST = new ResourceLocation("mocreatures", MoCreatures.proxy.MODEL_TEXTURE + "scorpionfrost.png");
    private static final ResourceLocation TEXTURE_FROSTSADDLE = new ResourceLocation("mocreatures", MoCreatures.proxy.MODEL_TEXTURE + "scorpionfrostsaddle.png");
    private static final ResourceLocation TEXTURE_UNDEAD = new ResourceLocation("mocreatures", MoCreatures.proxy.MODEL_TEXTURE + "scorpionundead.png");
    private static final ResourceLocation TEXTURE_UNDEADSADDLE = new ResourceLocation("mocreatures", MoCreatures.proxy.MODEL_TEXTURE + "scorpionundeadsaddle.png");

    public MoCRenderPetScorpion(MoCModelScorpion modelbase, float f)
    {
        super(modelbase, f);
    }

    @Override
    public void doRenderLiving(EntityLiving entityliving, double d, double d1, double d2, float f, float f1)
    {
        MoCEntityPetScorpion entityscorpion = (MoCEntityPetScorpion) entityliving;
        super.doRenderLiving(entityscorpion, d, d1, d2, f, f1);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entityliving, float f)
    {
        MoCEntityPetScorpion entityscorpion = (MoCEntityPetScorpion) entityliving;

        if (entityscorpion.climbing())
        {
            rotateAnimal(entityscorpion);
        }

        if (!entityscorpion.getIsAdult())
        {
            stretch(entityscorpion);
            if (entityscorpion.getIsPicked())
            {
                upsideDown(entityscorpion);
            }
        }
        else
        {
            adjustHeight(entityscorpion);
        }
    }

    protected void upsideDown(EntityLiving entityliving)
    {
        GL11.glRotatef(-90F, -1F, 0.0F, 0.0F);

        if (entityliving.ridingEntity == MoCClientProxy.mc.thePlayer)
        {
            GL11.glTranslatef(-0.55F, -1.9F, -0.7F);

        }
        else
        {
            GL11.glTranslatef(-1.555F, -0.5F, -0.5F);

        }

    }

    protected void adjustHeight(EntityLiving entityliving)
    {
        GL11.glTranslatef(0.0F, -0.1F, 0.0F);
    }

    protected void rotateAnimal(EntityLiving entityliving)
    {
        GL11.glRotatef(90F, -1F, 0.0F, 0.0F);
    }

    protected void stretch(MoCEntityPetScorpion entityscorpion)
    {

        float f = 1.1F;
        if (!entityscorpion.getIsAdult())
        {
            f = entityscorpion.getEdad() * 0.01F;
        }
        GL11.glScalef(f, f, f);
    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return this.getTexture((MoCEntityPetScorpion)par1Entity);
    }

    protected ResourceLocation getTexture(MoCEntityPetScorpion scorpion)
    {
        boolean saddle = scorpion.getIsRideable();
        switch (scorpion.getType())
        {
            case 1:
                if (!saddle) { return TEXTURE_DEFAULT; }
                return TEXTURE_DIRTSADDLE;
            case 2:
                if (!saddle) { return TEXTURE_CAVE; }
                return TEXTURE_CAVESADDLE;
            case 3:
                if (!saddle) { return TEXTURE_NETHER; }
                return TEXTURE_NETHERSADDLE;
            case 4:
                if (!saddle) { return TEXTURE_FROST; }
                return TEXTURE_FROSTSADDLE;
            case 5:
                if (!saddle) { return TEXTURE_UNDEAD; }
                return TEXTURE_UNDEADSADDLE;
            default:
                return TEXTURE_DEFAULT;
        }
    }
}
