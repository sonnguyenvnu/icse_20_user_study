public Set<String> findResourceNames(String location,URL locationUrl){
  Set<String> resourceNames=new TreeSet<>();
  Bundle bundle=getTargetBundleFromContextOrCurrent(FrameworkUtil.getBundle(getClass()),locationUrl);
  Enumeration<URL> entries=bundle.findEntries(locationUrl.getPath(),"*",true);
  if (entries != null) {
    while (entries.hasMoreElements()) {
      URL entry=entries.nextElement();
      String resourceName=getPathWithoutLeadingSlash(entry);
      resourceNames.add(resourceName);
    }
  }
  return resourceNames;
}
