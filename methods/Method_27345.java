@Nullable private static Intent getBlob(@NonNull Context context,@NonNull Uri uri){
  List<String> segments=uri.getPathSegments();
  if (segments == null || segments.size() < 4)   return null;
  String segmentTwo=segments.get(2);
  String extension=MimeTypeMap.getFileExtensionFromUrl(uri.toString());
  if (InputHelper.isEmpty(extension) || TextUtils.isDigitsOnly(extension)) {
    Uri urlBuilder=LinkParserHelper.getBlobBuilder(uri);
    return RepoFilesActivity.getIntent(context,urlBuilder.toString());
  }
  if (segmentTwo.equals("blob") || segmentTwo.equals("tree")) {
    Uri urlBuilder=getBlobBuilder(uri);
    Logger.e(urlBuilder);
    return CodeViewerActivity.createIntent(context,urlBuilder.toString(),uri.toString());
  }
 else {
    String authority=uri.getAuthority();
    if (TextUtils.equals(authority,RAW_AUTHORITY)) {
      return CodeViewerActivity.createIntent(context,uri.toString(),uri.toString());
    }
  }
  return null;
}
