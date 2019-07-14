@Override protected BaseItemResource<SimpleMovie,Movie> onAttachItemResource(long itemId,SimpleMovie simpleItem,Movie item){
  return MovieResource.attachTo(itemId,simpleItem,item,this);
}
