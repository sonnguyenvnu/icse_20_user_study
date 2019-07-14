public void add(int pos){
  final MainFragment ma=(MainFragment)((TabFragment)mainActivity.getSupportFragmentManager().findFragmentById(R.id.content_frame)).getCurrentTabFragment();
  final String path=ma.getCurrentPath();
switch (pos) {
case NEW_FOLDER:
    mkdir(ma.openMode,path,ma);
  break;
case NEW_FILE:
mkfile(ma.openMode,path,ma);
break;
case NEW_CLOUD:
BottomSheetDialogFragment fragment=new CloudSheetFragment();
fragment.show(ma.getActivity().getSupportFragmentManager(),CloudSheetFragment.TAG_FRAGMENT);
break;
}
}
