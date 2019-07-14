@Override public void showMediaGridFragment(){
  mMediaPreviewFragment=null;
  mMediaPageFragment=null;
  mSelectedIndex=0;
  FragmentTransaction ft=getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,mMediaGridFragment);
  if (mMediaPreviewFragment != null) {
    ft.hide(mMediaPreviewFragment);
  }
  if (mMediaPageFragment != null) {
    ft.hide(mMediaPageFragment);
  }
  ft.show(mMediaGridFragment).commit();
  if (mConfiguration.isImage()) {
    mTvToolbarTitle.setText(R.string.gallery_media_grid_image_title);
  }
 else {
    mTvToolbarTitle.setText(R.string.gallery_media_grid_video_title);
  }
}
