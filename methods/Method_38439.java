/** 
 * Registers new bundle that consist of provided list of source paths. Returns the real bundle id, as provided one is just a temporary bundle id.
 */
public synchronized String registerBundle(final String contextPath,final String actionPath,final String tempBundleId,final String bundleContentType,final List<String> sources){
  if (tempBundleId == null || sources.isEmpty()) {
    if (strategy == Strategy.ACTION_MANAGED) {
      actionBundles.put(actionPath,StringPool.EMPTY);
    }
    return null;
  }
  String[] sourcesArray=sources.toArray(new String[0]);
  for (int i=0, sourcesArrayLength=sourcesArray.length; i < sourcesArrayLength; i++) {
    sourcesArray[i]=sourcesArray[i].trim().toLowerCase();
  }
  if (sortResources) {
    Arrays.sort(sourcesArray);
  }
  StringBand sb=new StringBand(sourcesArray.length);
  for (  String src : sourcesArray) {
    sb.append(src);
  }
  String sourcesString=sb.toString();
  String bundleId=createDigest(sourcesString);
  bundleId+='.' + bundleContentType;
  if (strategy == Strategy.ACTION_MANAGED) {
    actionBundles.put(actionPath,bundleId);
    mirrors.put(tempBundleId,bundleId);
  }
  try {
    createBundle(contextPath,actionPath,bundleId,sources);
  }
 catch (  IOException ioex) {
    throw new HtmlStaplerException("Can't create bundle",ioex);
  }
  return bundleId;
}
