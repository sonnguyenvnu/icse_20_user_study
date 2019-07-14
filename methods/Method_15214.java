/** 
 * ??????
 */
public void selectPicFromCamera(){
  if (!CommonUtil.isExitsSdcard()) {
    showShortToast("SD?????????");
    return;
  }
  intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
  cameraFile=new File(DataKeeper.imagePath,"photo" + System.currentTimeMillis() + ".jpg");
  cameraFile.getParentFile().mkdirs();
  intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(cameraFile));
  toActivity(intent,REQUEST_CODE_CAMERA);
}
