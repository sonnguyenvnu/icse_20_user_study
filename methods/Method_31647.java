@Override public Collection<LoadableResource> scanForResources(){
  List<LoadableResource> resources=new ArrayList<>();
  String path=location.getPath();
  try {
    for (    String asset : context.getAssets().list(path)) {
      resources.add(new AndroidResource(location,context.getAssets(),path,asset,encoding));
    }
  }
 catch (  IOException e) {
    LOG.warn("Unable to scan for resources: " + e.getMessage());
  }
  return resources;
}
