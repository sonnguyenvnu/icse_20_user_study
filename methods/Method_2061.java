private boolean ensureExternalStoragePermissionGranted(){
  if (ContextCompat.checkSelfPermission(getContext(),Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
    ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PERMISSION_REQUEST_READ_EXTERNAL_STORAGE);
    return false;
  }
  return true;
}
