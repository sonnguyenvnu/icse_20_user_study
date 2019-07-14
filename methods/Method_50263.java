/** 
 * ????
 */
public static void setImageStoreDir(String imgFile){
  mImageStoreDir=new File(Environment.getExternalStorageDirectory(),"/DCIM" + File.separator + imgFile + File.separator);
  Logger.i("??????????" + mImageStoreDir.getAbsolutePath());
}
