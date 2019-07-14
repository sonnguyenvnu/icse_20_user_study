protected void onTypeSelected(Point leftBottom,Collection<Container.Entry> entries,String typeName){
  if (entries.size() == 1) {
    openCallback.accept(UriUtil.createURI(api,collectionOfFutureIndexes,entries.iterator().next(),null,typeName));
  }
 else {
    selectLocationController.show(new Point(leftBottom.x + (16 + 2),leftBottom.y + 2),entries,(entry) -> openCallback.accept(UriUtil.createURI(api,collectionOfFutureIndexes,entry,null,typeName)),() -> openTypeView.focus());
  }
}
