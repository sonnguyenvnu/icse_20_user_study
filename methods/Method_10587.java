public void btn(View view){
  int viewId=view.getId();
  if (viewId == R.id.top_mask) {
    light();
  }
 else   if (viewId == R.id.top_back) {
    finish();
  }
 else   if (viewId == R.id.top_openpicture) {
    RxPhotoTool.openLocalImage(mContext);
  }
}
