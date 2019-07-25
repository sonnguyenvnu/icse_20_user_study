public static CompoundNBT convert(TypeReference type,CompoundNBT cmp){
  int i=cmp.contains("DataVersion",99) ? cmp.getInt("DataVersion") : -1;
  return convert(type,cmp,i);
}
