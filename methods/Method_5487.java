/** 
 * Returns a fixed SmoothStreaming client manifest  {@link Uri}. 
 */
public static Uri fixManifestUri(Uri manifestUri){
  String lastPathSegment=manifestUri.getLastPathSegment();
  if (lastPathSegment != null && Util.toLowerInvariant(lastPathSegment).matches("manifest(\\(.+\\))?")) {
    return manifestUri;
  }
  return Uri.withAppendedPath(manifestUri,"Manifest");
}
