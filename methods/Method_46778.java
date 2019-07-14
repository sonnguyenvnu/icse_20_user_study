private void load(final File file){
  new Thread(() -> {
    File file1=getExternalCacheDir();
    if (!file.canRead() && isRootExplorer()) {
      try {
        RootUtils.copy(pathFile.getPath(),new File(file1.getPath(),file.getName()).getPath());
        pathFile=new File(file1.getPath(),file.getName());
      }
 catch (      ShellNotRunningException e) {
        e.printStackTrace();
      }
      delete=true;
    }
    try {
      sqLiteDatabase=SQLiteDatabase.openDatabase(pathFile.getPath(),null,SQLiteDatabase.OPEN_READONLY);
      c=sqLiteDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type='table'",null);
      arrayList=getDbTableNames(c);
      arrayAdapter=new ArrayAdapter(DatabaseViewerActivity.this,android.R.layout.simple_list_item_1,arrayList);
    }
 catch (    Exception e) {
      e.printStackTrace();
      finish();
    }
    runOnUiThread(() -> {
      listView.setAdapter(arrayAdapter);
    }
);
  }
).start();
}
