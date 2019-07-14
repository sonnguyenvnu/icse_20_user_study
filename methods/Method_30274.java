@Override protected BaseItemFragmentResource<SimpleBook,Book> onAttachResource(long itemId,SimpleBook simpleItem,Book item){
  return BookFragmentResource.attachTo(itemId,simpleItem,item,this);
}
