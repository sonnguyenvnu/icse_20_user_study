@Override public void onRequestPermissionsResultFragment(int requestCode,String[] permissions,int[] grantResults){
  if (requestCode == 6) {
    checkPermissions=false;
    if (currentViewNum == 0) {
      views[currentViewNum].onNextPressed();
    }
  }
}
