public static net.minecraft.nbt.CompoundTag convert(TypeReference type,net.minecraft.nbt.CompoundTag cmp){
  int i=cmp.containsKey("DataVersion",99) ? cmp.getInt("DataVersion") : -1;
  return convert(type,cmp,i);
}
