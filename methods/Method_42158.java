public static RvMediaFragment make(Album album){
  RvMediaFragment fragment=new RvMediaFragment();
  Bundle bundle=new Bundle();
  bundle.putParcelable(BUNDLE_ALBUM,album);
  fragment.setArguments(bundle);
  return fragment;
}
