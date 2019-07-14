public static boolean hasLocalAddress(Collection<String> endpoints){
  return endpoints.contains(getLoopbackAddress()) || endpoints.contains(getLocalAddress()) || endpoints.contains(getLocalHostName());
}
