@Override public boolean test(final LazyElementCell elementCell){
  final String group=elementCell.getGroup();
  return view.isEntity(group) || view.isEdge(group);
}
