private void setScaleType(){
  String scaleType=(String)mScaleType.getSelectedItem();
  if (TextUtils.equals(scaleType,getString(R.string.scale_center_crop))) {
    mBigImageView.setInitScaleType(BigImageView.INIT_SCALE_TYPE_CENTER_CROP);
  }
 else   if (TextUtils.equals(scaleType,getString(R.string.scale_center_inside))) {
    mBigImageView.setInitScaleType(BigImageView.INIT_SCALE_TYPE_CENTER_INSIDE);
  }
 else   if (TextUtils.equals(scaleType,getString(R.string.scale_custom))) {
    mBigImageView.setInitScaleType(BigImageView.INIT_SCALE_TYPE_CUSTOM);
  }
 else   if (TextUtils.equals(scaleType,getString(R.string.scale_start))) {
    mBigImageView.setInitScaleType(BigImageView.INIT_SCALE_TYPE_START);
  }
}
