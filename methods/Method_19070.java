@UiThread private void dataBoundRecursive(Section section){
  section.dataBound(section.getScopedContext());
  if (section.isDiffSectionSpec()) {
    return;
  }
  final List<Section> children=section.getChildren();
  for (int i=0, size=children.size(); i < size; i++) {
    dataBoundRecursive(children.get(i));
  }
}
