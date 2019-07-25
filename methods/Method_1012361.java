public static void start(Fragment fragment,String from,boolean isOne,boolean isCrop,ArrayList<SystemUtil.ImageItem> selectedImageItemList){
  Intent intent=new Intent(fragment.getContext(),PhotoSelectActivity.class);
  intent.putExtra(ConstantUtil.DATA,selectedImageItemList);
  intent.putExtra(ConstantUtil.IS_ONE,isOne);
  intent.putExtra(ConstantUtil.IS_CROP,isCrop);
  intent.putExtra(ConstantUtil.FROM,from);
  if (isOne) {
    fragment.startActivityForResult(intent,com.example.commonlibrary.utils.SystemUtil.REQUEST_CODE_ONE_PHOTO);
  }
 else {
    fragment.startActivity(intent);
  }
}
