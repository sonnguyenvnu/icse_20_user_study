@Override public void contribute(EditorCell cell,TreeBuilder builder){
  TreeBuilder basicProperties=builder.subtree().icon(CellExplorer.CellProperty).text("Basic Properties");
  CellLayout cellLayout=getCellLayout(cell);
  if (cellLayout != null) {
    basicProperties.property(CellExplorer.Cells,"cellLayout",cellLayout);
  }
  String text=getCellText(cell);
  if (text != null) {
    basicProperties.property("text",text);
  }
  Icon icon=getCellIcon(cell);
  if (icon != null) {
    basicProperties.property(icon,"icon",icon);
  }
  SNode node=cell.getSNode();
  if (node != null) {
    cell.getEditorComponent().getEditorContext().getRepository().getModelAccess().runReadAction(() -> {
      basicProperties.property(GlobalIconManager.getInstance().getIconFor(node),"node",toString(node));
    }
);
  }
  basicProperties.property("id",cell.getCellId()).property("class",cell.getClass().getName()).property("role",cell.getSRole() == null ? "<no role>" : cell.getSRole().getName()).property("x",cell.getX()).property("y",cell.getY()).property("width",cell.getWidth()).property("effectiveWidth",cell.getEffectiveWidth()).property("height",cell.getHeight()).property("baseline",cell.getBaseline()).property("ascent",cell.getAscent()).property("descent",cell.getDescent()).property("leftGap",cell.getLeftGap()).property("selectable",cell.isSelectable()).property("referenceCell",cell.isReferenceCell()).property("transformationMenuLookup",cell.getTransformationMenuLookup()).property("substituteInfo",cell.getSubstituteInfo());
}
