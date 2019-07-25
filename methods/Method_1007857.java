public static net.minecraft.nbt.CompoundTag convert(TypeReference type,net.minecraft.nbt.CompoundTag cmp,int sourceVer,int targetVer){
  if (sourceVer >= targetVer) {
    return cmp;
  }
  return (net.minecraft.nbt.CompoundTag)INSTANCE.fixer.update(type,new Dynamic<>(OPS_NBT,cmp),sourceVer,targetVer).getValue();
}
