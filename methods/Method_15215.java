/** 
 * ???????
 */
public void selectPicFromLocal(){
  Intent intent;
  if (Build.VERSION.SDK_INT < 19) {
    intent=new Intent(Intent.ACTION_GET_CONTENT);
    intent.setType("image/*");
  }
 else {
    intent=new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
  }
  toActivity(intent,REQUEST_CODE_LOCAL);
}
