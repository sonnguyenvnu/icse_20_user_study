/** 
 * ????????????JPG???
 * @param data     onActivityResult???????
 * @param filePath ????
 * @return ??
 */
public static File getTakePictureFile(Intent data,String filePath){
  if (data == null)   return null;
  Bundle extras=data.getExtras();
  if (extras == null)   return null;
  Bitmap photo=extras.getParcelable("data");
  File file=new File(filePath);
  if (RxImageTool.save(photo,file,Bitmap.CompressFormat.JPEG))   return file;
  return null;
}
