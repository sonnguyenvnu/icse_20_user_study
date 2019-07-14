@Nullable Map<String,Component> consumeAttachables(){
  @Nullable Map<String,Component> tmp=mAttachableContainer;
  mAttachableContainer=null;
  return tmp;
}
