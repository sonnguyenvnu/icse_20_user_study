public ProtectedResourceDetails loadProtectedResourceDetailsById(String id) throws IllegalArgumentException {
  return getResourceDetailsStore().get(id);
}
