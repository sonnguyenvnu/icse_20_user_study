@Override protected BaseItemResource<SimpleGame,Game> onAttachItemResource(long itemId,SimpleGame simpleItem,Game item){
  return GameResource.attachTo(itemId,simpleItem,item,this);
}
