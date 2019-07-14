@RequiresApi(Build.VERSION_CODES.LOLLIPOP) public static Intent createFileChooserIntent(WebChromeClient.FileChooserParams fileChooserParams){
  boolean allowMultiple=fileChooserParams.getMode() == WebChromeClient.FileChooserParams.MODE_OPEN_MULTIPLE;
  String[] acceptTypes=fileChooserParams.getAcceptTypes();
  if (acceptTypes != null && acceptTypes.length == 1 && acceptTypes[0].indexOf(',') != -1) {
    acceptTypes=acceptTypes[0].split(",");
  }
  return IntentUtils.makePickFile(acceptTypes,allowMultiple);
}
