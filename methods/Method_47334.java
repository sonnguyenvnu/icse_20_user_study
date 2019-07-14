public static void openFile(final File f,final MainActivity m,SharedPreferences sharedPrefs){
  boolean useNewStack=sharedPrefs.getBoolean(PreferencesConstants.PREFERENCE_TEXTEDITOR_NEWSTACK,false);
  boolean defaultHandler=isSelfDefault(f,m);
  SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(m);
  final Toast[] studioCount={null};
  if (f.getName().toLowerCase().endsWith(".apk")) {
    GeneralDialogCreation.showPackageDialog(f,m);
  }
 else   if (defaultHandler && CompressedHelper.isFileExtractable(f.getPath())) {
    GeneralDialogCreation.showArchiveDialog(f,m);
  }
 else   if (defaultHandler && f.getName().toLowerCase().endsWith(".db")) {
    Intent intent=new Intent(m,DatabaseViewerActivity.class);
    intent.putExtra("path",f.getPath());
    m.startActivity(intent);
  }
 else   if (Icons.getTypeOfFile(f.getPath(),f.isDirectory()) == Icons.AUDIO) {
    final int studio_count=sharedPreferences.getInt("studio",0);
    Uri uri=Uri.fromFile(f);
    final Intent intent=new Intent();
    intent.setAction(Intent.ACTION_VIEW);
    intent.setDataAndType(uri,"audio/*");
    if (studio_count != 0) {
      new CountDownTimer(studio_count,1000){
        @Override public void onTick(        long millisUntilFinished){
          int sec=(int)millisUntilFinished / 1000;
          if (studioCount[0] != null)           studioCount[0].cancel();
          studioCount[0]=Toast.makeText(m,sec + "",Toast.LENGTH_LONG);
          studioCount[0].show();
        }
        @Override public void onFinish(){
          if (studioCount[0] != null)           studioCount[0].cancel();
          studioCount[0]=Toast.makeText(m,m.getString(R.string.opening),Toast.LENGTH_LONG);
          studioCount[0].show();
          m.startActivity(intent);
        }
      }
.start();
    }
 else     m.startActivity(intent);
  }
 else {
    try {
      openunknown(f,m,false,useNewStack);
    }
 catch (    Exception e) {
      Toast.makeText(m,m.getString(R.string.noappfound),Toast.LENGTH_LONG).show();
      openWith(f,m,useNewStack);
    }
  }
}
