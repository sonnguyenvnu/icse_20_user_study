/** 
 * ????
 * @param path
 */
private void cutPicture(String path){
  if (StringUtil.isFilePath(path) == false) {
    Log.e(TAG,"cutPicture  StringUtil.isFilePath(path) == false >> showShortToast(?????);return;");
    showShortToast("?????");
    return;
  }
  toActivity(CutPictureActivity.createIntent(context,path,DataKeeper.imagePath,"photo" + System.currentTimeMillis(),200),REQUEST_TO_CUT_PICTURE);
}
