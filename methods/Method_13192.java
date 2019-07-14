@Override public String loadSource(API api,Container.Entry entry){
  if (isActivated(api)) {
    String filters=api.getPreferences().get(MavenOrgSourceLoaderPreferencesProvider.FILTERS);
    if ((filters == null) || filters.isEmpty()) {
      filters=MavenOrgSourceLoaderPreferencesProvider.DEFAULT_FILTERS_VALUE;
    }
    if (accepted(filters,entry.getPath())) {
      return searchSource(entry,downloadSourceJarFile(entry.getContainer().getRoot().getParent()));
    }
  }
  return null;
}
