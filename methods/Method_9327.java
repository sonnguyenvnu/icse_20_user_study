@Override public void saveSelfArgs(Bundle outState){
  try {
    Bundle bundle=new Bundle();
    bundle.putInt("currentViewNum",currentViewNum);
    bundle.putInt("syncContacts",syncContacts ? 1 : 0);
    for (int a=0; a <= currentViewNum; a++) {
      SlideView v=views[a];
      if (v != null) {
        v.saveStateParams(bundle);
      }
    }
    SharedPreferences preferences=ApplicationLoader.applicationContext.getSharedPreferences("logininfo2",Context.MODE_PRIVATE);
    SharedPreferences.Editor editor=preferences.edit();
    editor.clear();
    putBundleToEditor(bundle,editor,null);
    editor.commit();
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
