private void startCrop(String path,Uri uri){
  try {
    LaunchActivity activity=(LaunchActivity)parentFragment.getParentActivity();
    if (activity == null) {
      return;
    }
    Bundle args=new Bundle();
    if (path != null) {
      args.putString("photoPath",path);
    }
 else     if (uri != null) {
      args.putParcelable("photoUri",uri);
    }
    PhotoCropActivity photoCropActivity=new PhotoCropActivity(args);
    photoCropActivity.setDelegate(this);
    activity.presentFragment(photoCropActivity);
  }
 catch (  Exception e) {
    FileLog.e(e);
    Bitmap bitmap=ImageLoader.loadBitmap(path,uri,800,800,true);
    processBitmap(bitmap);
  }
}
