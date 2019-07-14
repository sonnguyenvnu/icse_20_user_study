/** 
 * Returns the necessary exports for the specified platform. If no 32 or 64-bit version of the exports exists, it returns the version that doesn't specify bit depth.
 */
public String[] getApplicationExportList(int platform,String variant){
  String platformName=PConstants.platformNames[platform];
  if (variant.equals("32")) {
    String[] pieces=exportList.get(platformName + "32");
    if (pieces != null)     return pieces;
  }
 else   if (variant.equals("64")) {
    String[] pieces=exportList.get(platformName + "64");
    if (pieces != null)     return pieces;
  }
 else   if (variant.equals("armv6hf")) {
    String[] pieces=exportList.get(platformName + "-armv6hf");
    if (pieces != null)     return pieces;
  }
 else   if (variant.equals("arm64")) {
    String[] pieces=exportList.get(platformName + "-arm64");
    if (pieces != null)     return pieces;
  }
  return exportList.get(platformName);
}
