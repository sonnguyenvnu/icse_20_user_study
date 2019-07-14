/** 
 * Locates target using path with various extensions appended.
 */
@Override protected String locateTarget(final ActionRequest actionRequest,String path){
  String target;
  if (path.endsWith(StringPool.SLASH)) {
    path=path + defaultViewPageName;
  }
  for (  final String ext : defaultViewExtensions) {
    target=path + ext;
    if (targetExists(actionRequest,target)) {
      return target;
    }
  }
  return null;
}
