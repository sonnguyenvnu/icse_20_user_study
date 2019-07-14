private void setInImageListInt(boolean inImageList){
  mInImageList=inImageList;
  mImageView.setRatio(mInImageList ? 1 : (6f / 5f));
  LayoutParams layoutParams=(LayoutParams)mImageView.getLayoutParams();
  layoutParams.width=mInImageList ? LayoutParams.WRAP_CONTENT : LayoutParams.MATCH_PARENT;
  layoutParams.height=mInImageList ? LayoutParams.MATCH_PARENT : LayoutParams.WRAP_CONTENT;
  mImageView.setLayoutParams(layoutParams);
}
