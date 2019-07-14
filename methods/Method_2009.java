private SimpleAdapter<Uri> getExternalPhotoSimpleAdapter(){
  if (ActivityCompat.checkSelfPermission(getContext(),Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
    return ContentProviderSimpleAdapter.getExternalPhotoSimpleAdapter(getActivity());
  }
 else {
    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_READ_EXTERNAL_ID);
  }
  return SimpleAdapter.Util.EMPTY_ADAPTER;
}
