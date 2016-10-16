package net.samagames.hub.interactions.graou;

import com.google.common.base.Predicate;
import net.minecraft.server.v1_10_R1.*;

import java.util.UUID;

class EntityGraou extends EntityOcelot
{
    private PathfinderGoalWalkToTile pathfinderGoalWalkToTile;

    EntityGraou(World world)
    {
        super(world);

        this.setCatType(3);
        this.setAI(false);
    }

    public void postInit(float yaw, float pitch)
    {
        super.setYawPitch(yaw, pitch);

        this.setTamed(true);
        this.setOwnerUUID(UUID.randomUUID());
        this.setSitting(true);
        this.persistent = true;
    }

    @Override
    protected void r()
    {
        this.pathfinderGoalWalkToTile = new PathfinderGoalWalkToTile(this, 1.0F);

        this.goalSit = new PathfinderGoalSit(this);
        this.goalSelector.a(1, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 10.0F));
        this.goalSelector.a(2, this.pathfinderGoalWalkToTile);
    }

    @Override
    protected void df() {}

    @Override
    public void b(NBTTagCompound nbttagcompound) {}

    @Override
    public boolean c(NBTTagCompound nbttagcompound)
    {
        return false;
    }

    @Override
    public void a(NBTTagCompound nbttagcompound) {}

    @Override
    public boolean d(NBTTagCompound nbttagcompound)
    {
        return false;
    }

    @Override
    public void f(NBTTagCompound nbttagcompound) {}

    @Override
    protected SoundEffect G()
    {
        return SoundEffects.T;
    }

    public PathfinderGoalWalkToTile getPathfinderGoalWalkToTile()
    {
        return this.pathfinderGoalWalkToTile;
    }
}
