@Nullable @Override public BracePair find(@NotNull EditorCell cellToSelect){
  EditorCell_Collection collection=getCollectionToHighlight(cellToSelect);
  if (collection == null) {
    return null;
  }
  return new BracePair(collection.firstContentCell(),collection.lastContentCell(),collection.getStyle().get(StyleAttributes.SHOW_BOUNDARIES_IN));
}
