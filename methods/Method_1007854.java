public static net.minecraft.nbt.CompoundTag convert(LegacyType type,net.minecraft.nbt.CompoundTag cmp,int sourceVer){
  return convert(type.getDFUType(),cmp,sourceVer);
}
