@Override public SelectionCoreImpl raw(final String text){
  return addToCurrentColumn(new RawText(text));
}
