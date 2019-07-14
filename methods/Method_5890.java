private static String getAdaptiveSupportString(int trackCount,int adaptiveSupport){
  if (trackCount < 2) {
    return "N/A";
  }
switch (adaptiveSupport) {
case RendererCapabilities.ADAPTIVE_SEAMLESS:
    return "YES";
case RendererCapabilities.ADAPTIVE_NOT_SEAMLESS:
  return "YES_NOT_SEAMLESS";
case RendererCapabilities.ADAPTIVE_NOT_SUPPORTED:
return "NO";
default :
return "?";
}
}
