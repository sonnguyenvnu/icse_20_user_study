public static net.minecraft.nbt.CompoundTag convert(LegacyType type,net.minecraft.nbt.CompoundTag cmp,int sourceVer,int targetVer){
  return convert(type.getDFUType(),cmp,sourceVer,targetVer);
}
