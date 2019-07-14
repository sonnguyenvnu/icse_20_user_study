public static Uri[] parseFileChooserResult(int resultCode,Intent data){
  if (resultCode != Activity.RESULT_OK || data == null) {
    return null;
  }
  if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
    ClipData clipData=data.getClipData();
    if (clipData != null) {
      int itemCount=clipData.getItemCount();
      if (itemCount > 0) {
        Uri[] uris=new Uri[itemCount];
        for (int i=0; i < itemCount; ++i) {
          uris[i]=clipData.getItemAt(i).getUri();
        }
        return uris;
      }
    }
  }
  Uri uri=data.getData();
  if (uri != null) {
    return new Uri[]{uri};
  }
  return null;
}
