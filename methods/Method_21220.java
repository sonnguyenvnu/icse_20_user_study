private @NonNull List<List<Object>> sectionsFromData(final @NonNull NavigationDrawerData data){
  final List<List<Object>> newSections=new ArrayList<>();
  newSections.add(Collections.singletonList(data.user()));
  newSections.add(Collections.singletonList(null));
  newSections.add(Collections.singletonList(R.string.Collections));
  final List<NavigationDrawerData.Section> topFilterSections=Observable.from(data.sections()).filter(NavigationDrawerData.Section::isTopFilter).toList().toBlocking().single();
  final List<NavigationDrawerData.Section> categoryFilterSections=Observable.from(data.sections()).filter(NavigationDrawerData.Section::isCategoryFilter).toList().toBlocking().single();
  for (  final NavigationDrawerData.Section section : topFilterSections) {
    newSections.add(new ArrayList<>(section.rows()));
  }
  newSections.add(Collections.singletonList(null));
  newSections.add(Collections.singletonList(R.string.discovery_filters_categories_title));
  for (  final NavigationDrawerData.Section section : categoryFilterSections) {
    newSections.add(new ArrayList<>(section.rows()));
  }
  return newSections;
}
