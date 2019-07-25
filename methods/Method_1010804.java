@Nullable @Override public EditorCell locate(@NotNull EditorContext editorContext){
  EditorCell parent=myParentLocator.locate(editorContext);
  if (!(parent instanceof EditorCell_Collection)) {
    return null;
  }
  EditorCell_Collection parentCollection=(EditorCell_Collection)parent;
  if (myChildIndex < 0 || myChildIndex >= parentCollection.getCellsCount()) {
    return null;
  }
  return IterableUtil.get(parentCollection,myChildIndex);
}
