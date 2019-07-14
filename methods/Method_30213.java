@Override protected BaseItemResource<SimpleMusic,Music> onAttachItemResource(long itemId,SimpleMusic simpleItem,Music item){
  return MusicResource.attachTo(itemId,simpleItem,item,this);
}
