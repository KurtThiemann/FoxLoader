package com.fox2code.foxloader.server.mixins;

import com.fox2code.foxloader.network.NetworkPlayer;
import com.fox2code.foxloader.registry.RegisteredEntity;
import com.fox2code.foxloader.registry.RegisteredTileEntity;
import com.fox2code.foxloader.registry.RegisteredWorld;
import net.minecraft.src.game.block.tileentity.TileEntity;
import net.minecraft.src.game.entity.Entity;
import net.minecraft.src.game.entity.player.EntityPlayer;
import net.minecraft.src.game.level.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(World.class)
public abstract class MixinWorld implements RegisteredWorld {
    @Shadow public abstract int getBlockId(int x, int y, int z);
    @Shadow public abstract int getBlockMetadata(int xCoord, int yCoord, int zCoord);
    @Shadow public abstract boolean setBlockAndMetadataWithNotify(int xCoord, int yCoord, int zCoord, int block, int metadata);

    @Shadow public List<EntityPlayer> playerEntities;

    @Shadow public List<TileEntity> loadedTileEntityList;

    @Shadow public List<Entity> loadedEntityList;

    @Override
    public boolean hasRegisteredControl() {
        return true;
    }

    @Override
    public int getRegisteredBlockId(int xCoord, int yCoord, int zCoord) {
        return this.getBlockId(xCoord, yCoord, zCoord);
    }

    @Override
    public int getRegisteredBlockMetadata(int xCoord, int yCoord, int zCoord) {
        return this.getBlockMetadata(xCoord, yCoord, zCoord);
    }

    @Override
    public void setRegisteredBlockAndMetadataWithNotify(int xCoord, int yCoord, int zCoord, int block, int metadata) {
        this.setBlockAndMetadataWithNotify(xCoord, yCoord, zCoord, block, metadata);
    }

    @Override
    public void forceSetRegisteredBlockAndMetadataWithNotify(int xCoord, int yCoord, int zCoord, int block, int metadata) {
        this.setBlockAndMetadataWithNotify(xCoord, yCoord, zCoord, block, metadata);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<? extends RegisteredEntity> getRegisteredEntities() {
        return (List<? extends RegisteredEntity>) (Object) this.loadedEntityList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<? extends RegisteredTileEntity> getRegisteredTileEntities() {
        return (List<? extends RegisteredTileEntity>) (Object) this.loadedTileEntityList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<? extends NetworkPlayer> getRegisteredNetworkPlayers() {
        return (List<? extends NetworkPlayer>) (Object) this.playerEntities;
    }
}
