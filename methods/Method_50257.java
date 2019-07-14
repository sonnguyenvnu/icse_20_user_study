@Override public void showMediaPageFragment(ArrayList<MediaBean> list,int position){
  mSelectedIndex=1;
  FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
  mMediaPageFragment=MediaPageFragment.newInstance(mConfiguration,list,position);
  ft.add(R.id.fragment_container,mMediaPageFragment);
  mMediaPreviewFragment=null;
  ft.hide(mMediaGridFragment);
  ft.show(mMediaPageFragment);
  ft.commit();
  String title=getString(R.string.gallery_page_title,position + 1,list.size());
  mTvToolbarTitle.setText(title);
}
