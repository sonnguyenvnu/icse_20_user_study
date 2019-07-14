@Override public InputStream getInputStream(@NonNull Context context){
  try {
    return context.getAssets().open(assetName);
  }
 catch (  IOException e) {
    ACRA.log.e(LOG_TAG,"Could not open certificate in asset://" + assetName,e);
  }
  return null;
}
