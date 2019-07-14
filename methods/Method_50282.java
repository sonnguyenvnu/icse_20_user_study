/** 
 * onAttach ? onStart
 */
public void onLoadFile(){
  if (getImageStoreDirByFile() == null && getImageStoreDirByStr() == null) {
    mImageStoreDir=new File(Environment.getExternalStorageDirectory(),"/DCIM/IMMQY/");
    setImageStoreCropDir(mImageStoreDir);
  }
  if (!mImageStoreDir.exists()) {
    mImageStoreDir.mkdirs();
  }
  if (getImageStoreCropDirByFile() == null && getImageStoreCropDirByStr() == null) {
    mImageStoreCropDir=new File(mImageStoreDir,"crop");
    if (!mImageStoreCropDir.exists()) {
      mImageStoreCropDir.mkdirs();
    }
    setImageStoreCropDir(mImageStoreCropDir);
  }
}
