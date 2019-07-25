private boolean accepts(final Class<?> clazz){
  final String type=clazz.getCanonicalName();
  if (type != null) {
    for (    final String packageName : getTypeRepository().getPackages()) {
      if (type.startsWith(packageName)) {
        return true;
      }
    }
  }
  return false;
}
