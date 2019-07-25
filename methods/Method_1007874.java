public static BaseItemStack adapt(ItemStack itemStack){
  CompoundTag tag=NBTConverter.fromNative(itemStack.serializeNBT());
  if (tag.getValue().isEmpty()) {
    tag=null;
  }
 else {
    final Tag tagTag=tag.getValue().get("tag");
    if (tagTag instanceof CompoundTag) {
      tag=((CompoundTag)tagTag);
    }
 else {
      tag=null;
    }
  }
  return new BaseItemStack(adapt(itemStack.getItem()),tag,itemStack.getCount());
}
