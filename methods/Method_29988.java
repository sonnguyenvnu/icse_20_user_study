private List<Uri> parsePickOrCaptureImageResult(Intent data){
  if (data != null) {
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
      ClipData clipData=data.getClipData();
      if (clipData != null) {
        int itemCount=clipData.getItemCount();
        if (itemCount > 0) {
          List<Uri> uris=new ArrayList<>();
          for (int i=0; i < itemCount; ++i) {
            uris.add(clipData.getItemAt(i).getUri());
          }
          return uris;
        }
      }
    }
    Uri uri=data.getData();
    if (uri != null) {
      return Collections.singletonList(uri);
    }
  }
  if (mCaptureImageOutputFile != null) {
    getActivity().sendBroadcast(IntentUtils.makeMediaScan(mCaptureImageOutputFile));
    return Collections.singletonList(Uri.fromFile(mCaptureImageOutputFile));
  }
  return Collections.emptyList();
}
