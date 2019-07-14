public void goToMain(String path){
  android.support.v4.app.FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
  TabFragment tabFragment=new TabFragment();
  if (path != null && path.length() > 0) {
    Bundle b=new Bundle();
    b.putString("path",path);
    tabFragment.setArguments(b);
  }
  transaction.replace(R.id.content_frame,tabFragment);
  drawer.setSomethingSelected(true);
  transaction.addToBackStack("tabt" + 1);
  transaction.commitAllowingStateLoss();
  appbar.setTitle(null);
  floatingActionButton.setVisibility(View.VISIBLE);
  floatingActionButton.getMenuButton().show();
  if (openzip && zippath != null) {
    openCompressed(zippath);
    zippath=null;
  }
}
