@Override protected BaseItemFragmentResource<SimpleGame,Game> onAttachResource(long itemId,SimpleGame simpleItem,Game item){
  return GameFragmentResource.attachTo(itemId,simpleItem,item,this);
}
