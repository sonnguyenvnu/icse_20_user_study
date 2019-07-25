public void select(SelectState select){
  selected=select;
  if (selected == SelectState.SELECTED && priority.getType() == FilePriority.Type.IGNORE)   setPriority(new FilePriority(FilePriority.Type.NORMAL));
 else   if (selected == SelectState.UNSELECTED && priority.getType() != FilePriority.Type.IGNORE)   setPriority(new FilePriority(FilePriority.Type.IGNORE));
  if (parent != null && parent.selected != select)   parent.onChildSelectChange();
  if (children.size() != 0)   for (  TorrentContentFileTree node : children.values())   if (node.selected != select)   node.select(select);
}
