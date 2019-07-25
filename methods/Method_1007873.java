public static ItemStack adapt(BaseItemStack baseItemStack){
  CompoundNBT forgeCompound=null;
  if (baseItemStack.getNbtData() != null) {
    forgeCompound=NBTConverter.toNative(baseItemStack.getNbtData());
  }
  final ItemStack itemStack=new ItemStack(adapt(baseItemStack.getType()),baseItemStack.getAmount());
  itemStack.setTag(forgeCompound);
  return itemStack;
}
