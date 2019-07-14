@Override public Point init(Context context,Uri uri) throws Exception {
  String uriString=uri.toString();
  if (uriString.startsWith(RESOURCE_PREFIX)) {
    Resources res;
    String packageName=uri.getAuthority();
    if (context.getPackageName().equals(packageName)) {
      res=context.getResources();
    }
 else {
      PackageManager pm=context.getPackageManager();
      res=pm.getResourcesForApplication(packageName);
    }
    int id=0;
    List<String> segments=uri.getPathSegments();
    int size=segments.size();
    if (size == 2 && segments.get(0).equals("drawable")) {
      String resName=segments.get(1);
      id=res.getIdentifier(resName,"drawable",packageName);
    }
 else     if (size == 1 && TextUtils.isDigitsOnly(segments.get(0))) {
      try {
        id=Integer.parseInt(segments.get(0));
      }
 catch (      NumberFormatException ignored) {
      }
    }
    decoder=BitmapRegionDecoder.newInstance(context.getResources().openRawResource(id),false);
  }
 else   if (uriString.startsWith(ASSET_PREFIX)) {
    String assetName=uriString.substring(ASSET_PREFIX.length());
    decoder=BitmapRegionDecoder.newInstance(context.getAssets().open(assetName,AssetManager.ACCESS_RANDOM),false);
  }
 else   if (uriString.startsWith(FILE_PREFIX)) {
    decoder=BitmapRegionDecoder.newInstance(uriString.substring(FILE_PREFIX.length()),false);
  }
 else {
    InputStream inputStream=null;
    try {
      ContentResolver contentResolver=context.getContentResolver();
      inputStream=contentResolver.openInputStream(uri);
      decoder=BitmapRegionDecoder.newInstance(inputStream,false);
    }
  finally {
      if (inputStream != null) {
        try {
          inputStream.close();
        }
 catch (        Exception e) {
        }
      }
    }
  }
  return new Point(decoder.getWidth(),decoder.getHeight());
}
