private boolean isGroupItem(T item){
  return item != null && item instanceof RecursiveTreeObject && item.getClass() == RecursiveTreeObject.class;
}
