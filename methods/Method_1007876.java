public static CompoundNBT convert(LegacyType type,CompoundNBT cmp,int sourceVer,int targetVer){
  return convert(type.getDFUType(),cmp,sourceVer,targetVer);
}
