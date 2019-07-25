public static ItemStack adapt(BaseItemStack baseItemStack){
  net.minecraft.nbt.CompoundTag fabricCompound=null;
  if (baseItemStack.getNbtData() != null) {
    fabricCompound=NBTConverter.toNative(baseItemStack.getNbtData());
  }
  final ItemStack itemStack=new ItemStack(adapt(baseItemStack.getType()),baseItemStack.getAmount());
  itemStack.setTag(fabricCompound);
  return itemStack;
}
