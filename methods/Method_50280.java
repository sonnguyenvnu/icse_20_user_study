/** 
 * Observable????
 */
public void refreshUI(){
  try {
    Logger.i("->getImageStoreDirByFile().getPath().toString()?" + getImageStoreDirByFile().getPath());
    Logger.i("->getImageStoreCropDirByStr ().toString()?" + getImageStoreCropDirByStr());
    if (!TextUtils.isEmpty(mImagePath))     mMediaScanner.scanFile(mImagePath,IMAGE_TYPE,this);
    if (mCropPath != null) {
      Logger.i("->mCropPath:" + mCropPath.getPath() + " " + IMAGE_TYPE);
      mMediaScanner.scanFile(mCropPath.getPath(),IMAGE_TYPE,this);
    }
  }
 catch (  Exception e) {
    Logger.e(e.getMessage());
  }
}
