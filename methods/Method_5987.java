/** 
 * Performs relative resolution of a  {@code referenceUri} with respect to a {@code baseUri}. <p> The resolution is performed as specified by RFC-3986.
 * @param baseUri The base URI.
 * @param referenceUri The reference URI to resolve.
 */
public static String resolve(String baseUri,String referenceUri){
  StringBuilder uri=new StringBuilder();
  baseUri=baseUri == null ? "" : baseUri;
  referenceUri=referenceUri == null ? "" : referenceUri;
  int[] refIndices=getUriIndices(referenceUri);
  if (refIndices[SCHEME_COLON] != -1) {
    uri.append(referenceUri);
    removeDotSegments(uri,refIndices[PATH],refIndices[QUERY]);
    return uri.toString();
  }
  int[] baseIndices=getUriIndices(baseUri);
  if (refIndices[FRAGMENT] == 0) {
    return uri.append(baseUri,0,baseIndices[FRAGMENT]).append(referenceUri).toString();
  }
  if (refIndices[QUERY] == 0) {
    return uri.append(baseUri,0,baseIndices[QUERY]).append(referenceUri).toString();
  }
  if (refIndices[PATH] != 0) {
    int baseLimit=baseIndices[SCHEME_COLON] + 1;
    uri.append(baseUri,0,baseLimit).append(referenceUri);
    return removeDotSegments(uri,baseLimit + refIndices[PATH],baseLimit + refIndices[QUERY]);
  }
  if (referenceUri.charAt(refIndices[PATH]) == '/') {
    uri.append(baseUri,0,baseIndices[PATH]).append(referenceUri);
    return removeDotSegments(uri,baseIndices[PATH],baseIndices[PATH] + refIndices[QUERY]);
  }
  if (baseIndices[SCHEME_COLON] + 2 < baseIndices[PATH] && baseIndices[PATH] == baseIndices[QUERY]) {
    uri.append(baseUri,0,baseIndices[PATH]).append('/').append(referenceUri);
    return removeDotSegments(uri,baseIndices[PATH],baseIndices[PATH] + refIndices[QUERY] + 1);
  }
 else {
    int lastSlashIndex=baseUri.lastIndexOf('/',baseIndices[QUERY] - 1);
    int baseLimit=lastSlashIndex == -1 ? baseIndices[PATH] : lastSlashIndex + 1;
    uri.append(baseUri,0,baseLimit).append(referenceUri);
    return removeDotSegments(uri,baseIndices[PATH],baseLimit + refIndices[QUERY]);
  }
}
