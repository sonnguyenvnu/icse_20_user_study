public void displayMedia(Album album){
  unreferenceFragments();
  rvMediaFragment=RvMediaFragment.make(album);
  fragmentMode=FragmentMode.MODE_MEDIA;
  lockNavigationDrawer();
  rvMediaFragment.setListener(this);
  getSupportFragmentManager().beginTransaction().replace(R.id.content,rvMediaFragment,RvMediaFragment.TAG).addToBackStack(null).commit();
}
