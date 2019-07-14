/** 
 * Calculates indices of the constituent components of a URI.
 * @param uriString The URI as a string.
 * @return The corresponding indices.
 */
private static int[] getUriIndices(String uriString){
  int[] indices=new int[INDEX_COUNT];
  if (TextUtils.isEmpty(uriString)) {
    indices[SCHEME_COLON]=-1;
    return indices;
  }
  int length=uriString.length();
  int fragmentIndex=uriString.indexOf('#');
  if (fragmentIndex == -1) {
    fragmentIndex=length;
  }
  int queryIndex=uriString.indexOf('?');
  if (queryIndex == -1 || queryIndex > fragmentIndex) {
    queryIndex=fragmentIndex;
  }
  int schemeIndexLimit=uriString.indexOf('/');
  if (schemeIndexLimit == -1 || schemeIndexLimit > queryIndex) {
    schemeIndexLimit=queryIndex;
  }
  int schemeIndex=uriString.indexOf(':');
  if (schemeIndex > schemeIndexLimit) {
    schemeIndex=-1;
  }
  boolean hasAuthority=schemeIndex + 2 < queryIndex && uriString.charAt(schemeIndex + 1) == '/' && uriString.charAt(schemeIndex + 2) == '/';
  int pathIndex;
  if (hasAuthority) {
    pathIndex=uriString.indexOf('/',schemeIndex + 3);
    if (pathIndex == -1 || pathIndex > queryIndex) {
      pathIndex=queryIndex;
    }
  }
 else {
    pathIndex=schemeIndex + 1;
  }
  indices[SCHEME_COLON]=schemeIndex;
  indices[PATH]=pathIndex;
  indices[QUERY]=queryIndex;
  indices[FRAGMENT]=fragmentIndex;
  return indices;
}
