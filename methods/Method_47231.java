public void onDrawerClosed(){
  if (pending_fragmentTransaction != null) {
    pending_fragmentTransaction.commit();
    pending_fragmentTransaction=null;
  }
  if (pendingPath != null) {
    HybridFile hFile=new HybridFile(OpenMode.UNKNOWN,pendingPath);
    hFile.generateMode(mainActivity);
    if (hFile.isSimpleFile()) {
      FileUtils.openFile(new File(pendingPath),mainActivity,mainActivity.getPrefs());
      pendingPath=null;
      return;
    }
    MainFragment mainFrag=mainActivity.getCurrentMainFragment();
    if (mainFrag != null) {
      mainFrag.loadlist(pendingPath,false,OpenMode.UNKNOWN);
    }
 else {
      mainActivity.goToMain(pendingPath);
      return;
    }
    pendingPath=null;
  }
  mainActivity.supportInvalidateOptionsMenu();
}
