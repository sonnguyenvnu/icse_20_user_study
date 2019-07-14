/** 
 * ?????????
 * @param context ???
 * @param data    onActivityResult???Intent
 * @return bitmap
 */
public static Bitmap getChoosedImage(Activity context,Intent data){
  if (data == null)   return null;
  Bitmap bm=null;
  ContentResolver cr=context.getContentResolver();
  Uri originalUri=data.getData();
  try {
    bm=MediaStore.Images.Media.getBitmap(cr,originalUri);
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
  return bm;
}
