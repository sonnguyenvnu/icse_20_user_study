@Override public void contribute(EditorCell cell,TreeBuilder builder){
  Style style=cell.getStyle();
  if (style == null)   return;
  TreeBuilder subtree=builder.subtree().icon(CellExplorer.CellDefault).text("Styles");
  for (  StyleAttribute attribute : style.getSpecifiedAttributes()) {
    String name=attribute.getName();
    Object value=style.get(attribute);
    subtree.property(name,value);
  }
}
