public void unapply(Style toStyle,EditorCell editorCell){
  Style toRemove=new StyleImpl();
  apply(toRemove,editorCell);
  toStyle.removeAll(toRemove);
}
