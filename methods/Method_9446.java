@Override public boolean onFragmentCreate(){
  swipeBackEnabled=false;
  if (imageToCrop == null) {
    String photoPath=getArguments().getString("photoPath");
    Uri photoUri=getArguments().getParcelable("photoUri");
    if (photoPath == null && photoUri == null) {
      return false;
    }
    if (photoPath != null) {
      File f=new File(photoPath);
      if (!f.exists()) {
        return false;
      }
    }
    int size;
    if (AndroidUtilities.isTablet()) {
      size=AndroidUtilities.dp(520);
    }
 else {
      size=Math.max(AndroidUtilities.displaySize.x,AndroidUtilities.displaySize.y);
    }
    imageToCrop=ImageLoader.loadBitmap(photoPath,photoUri,size,size,true);
    if (imageToCrop == null) {
      return false;
    }
  }
  drawable=new BitmapDrawable(imageToCrop);
  super.onFragmentCreate();
  return true;
}
