@OnClick(R.id.btn_pick_image) void launchImagePicker(){
  Intent intent=new Intent(Intent.ACTION_GET_CONTENT,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
  intent.setType("image/*");
  intent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
  startActivityForResult(Intent.createChooser(intent,"Select Image"),REQUEST_SELECT_IMAGE);
}
