/** 
 * ????????????Intent
 */
public static Intent getImagePickerIntent(){
  Intent intent=new Intent(Intent.ACTION_PICK,null);
  return intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
}
