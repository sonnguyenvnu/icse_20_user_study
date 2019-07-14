@Override protected BaseItemFragmentResource<SimpleMovie,Movie> onAttachResource(long itemId,SimpleMovie simpleItem,Movie item){
  return MovieFragmentResource.attachTo(itemId,simpleItem,item,this);
}
