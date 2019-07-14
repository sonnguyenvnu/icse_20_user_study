@Override protected int layout(final @NonNull SectionRow sectionRow){
  final Object object=objectFromSectionRow(sectionRow);
switch (sectionRow.section()) {
case 0:
    return (object == null) ? R.layout.discovery_drawer_logged_out_view : R.layout.discovery_drawer_logged_in_view;
default :
  return layoutForDatum(object,sectionRow);
}
}
