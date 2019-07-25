public void pick(Fragment fragment,int requestCode){
  fragment.startActivityForResult(buildPickIntent(fragment.getActivity()),requestCode);
}
