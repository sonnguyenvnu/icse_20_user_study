public static Intent createFileChooserIntent(String acceptType){
  if (acceptType != null) {
    acceptType=acceptType.trim();
  }
  String[] acceptTypes=!TextUtils.isEmpty(acceptType) ? acceptType.split(",") : null;
  return IntentUtils.makePickFile(acceptTypes,false);
}
