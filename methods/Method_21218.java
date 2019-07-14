@Override protected @Nullable Object objectFromSectionRow(final @NonNull SectionRow sectionRow){
  final Object object=super.objectFromSectionRow(sectionRow);
  if (object == null) {
    return null;
  }
  if (object instanceof User || object instanceof Integer) {
    return object;
  }
  final NavigationDrawerData.Section.Row row=(NavigationDrawerData.Section.Row)object;
  final boolean expanded;
  if (row.params().category() == null || this.drawerData.expandedCategory() == null) {
    expanded=false;
  }
 else {
    expanded=row.params().category().rootId() == this.drawerData.expandedCategory().rootId();
  }
  return row.toBuilder().selected(row.params().equals(this.drawerData.selectedParams())).rootIsExpanded(expanded).build();
}
