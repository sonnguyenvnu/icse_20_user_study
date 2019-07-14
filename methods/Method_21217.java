private int layoutForDatum(final @NonNull Object datum,final @NonNull SectionRow sectionRow){
  if (datum instanceof NavigationDrawerData.Section.Row) {
    final NavigationDrawerData.Section.Row row=(NavigationDrawerData.Section.Row)datum;
    if (sectionRow.row() == 0) {
      return row.params().isCategorySet() ? R.layout.discovery_drawer_parent_filter_view : R.layout.discovery_drawer_top_filter_view;
    }
 else {
      return R.layout.discovery_drawer_child_filter_view;
    }
  }
 else   if (datum instanceof Integer) {
    return R.layout.discovery_drawer_header;
  }
  return R.layout.horizontal_line_1dp_view;
}
