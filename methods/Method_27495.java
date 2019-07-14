@Override public void hideProgress(){
  ProgressDialogFragment fragment=(ProgressDialogFragment)AppHelper.getFragmentByTag(getSupportFragmentManager(),ProgressDialogFragment.TAG);
  if (fragment != null) {
    isProgressShowing=false;
    fragment.dismiss();
  }
}
