public static CompoundNBT convert(TypeReference type,CompoundNBT cmp,int sourceVer,int targetVer){
  if (sourceVer >= targetVer) {
    return cmp;
  }
  return (CompoundNBT)INSTANCE.fixer.update(type,new Dynamic<>(OPS_NBT,cmp),sourceVer,targetVer).getValue();
}
