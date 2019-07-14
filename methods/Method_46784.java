@Override public void onBackPressed(){
  if (!drawer.isLocked() && drawer.isOpen()) {
    drawer.close();
    return;
  }
  Fragment fragment=getFragmentAtFrame();
  if (getAppbar().getSearchView().isShown()) {
    getAppbar().getSearchView().hideSearchView();
  }
 else   if (fragment instanceof TabFragment) {
    if (floatingActionButton.isExpanded()) {
      floatingActionButton.collapse();
    }
 else {
      getCurrentMainFragment().goBack();
    }
  }
 else   if (fragment instanceof CompressedExplorerFragment) {
    CompressedExplorerFragment compressedExplorerFragment=(CompressedExplorerFragment)getFragmentAtFrame();
    if (compressedExplorerFragment.mActionMode == null) {
      if (compressedExplorerFragment.canGoBack()) {
        compressedExplorerFragment.goBack();
      }
 else       if (openzip) {
        openzip=false;
        finish();
      }
 else {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_out_bottom,R.anim.slide_out_bottom);
        fragmentTransaction.remove(compressedExplorerFragment);
        fragmentTransaction.commit();
        supportInvalidateOptionsMenu();
        floatingActionButton.setVisibility(View.VISIBLE);
        floatingActionButton.getMenuButton().show();
      }
    }
 else {
      compressedExplorerFragment.mActionMode.finish();
    }
  }
 else   if (fragment instanceof FtpServerFragment) {
    if (path != null && path.length() > 0) {
      HybridFile file=new HybridFile(OpenMode.UNKNOWN,path);
      file.generateMode(this);
      if (file.isDirectory(this))       goToMain(path);
 else {
        goToMain(null);
        FileUtils.openFile(new File(path),this,getPrefs());
      }
    }
 else {
      goToMain(null);
    }
  }
 else {
    goToMain(null);
  }
}
