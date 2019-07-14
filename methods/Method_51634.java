private TreeCellRenderer createNoImageTreeCellRenderer(){
  DefaultTreeCellRenderer treeCellRenderer=new DefaultTreeCellRenderer();
  treeCellRenderer.setLeafIcon(null);
  treeCellRenderer.setOpenIcon(null);
  treeCellRenderer.setClosedIcon(null);
  return treeCellRenderer;
}
