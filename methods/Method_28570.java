private void startActivity(@Nullable Uri url){
  if (url == null)   return;
  if (MarkDownProvider.isImage(url.toString())) {
    CodeViewerActivity.startActivity(getContext(),url.toString(),url.toString());
  }
 else {
    String lastSegment=url.getEncodedFragment();
    if (lastSegment != null || url.toString().startsWith("#") || url.toString().indexOf('#') != -1) {
      return;
    }
    SchemeParser.launchUri(getContext(),url,true);
  }
}
