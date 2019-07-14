private void loadPlugin(Context base){
  if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
    Toast.makeText(this,"sdcard was NOT MOUNTED!",Toast.LENGTH_SHORT).show();
  }
  PluginManager pluginManager=PluginManager.getInstance(base);
  File apk=new File(Environment.getExternalStorageDirectory(),"Test.apk");
  if (apk.exists()) {
    try {
      pluginManager.loadPlugin(apk);
      Log.i(TAG,"Loaded plugin from apk: " + apk);
    }
 catch (    Exception e) {
      e.printStackTrace();
    }
  }
 else {
    try {
      File file=new File(base.getFilesDir(),"Test.apk");
      java.io.InputStream inputStream=base.getAssets().open("Test.apk",2);
      java.io.FileOutputStream outputStream=new java.io.FileOutputStream(file);
      byte[] buf=new byte[1024];
      int len;
      while ((len=inputStream.read(buf)) > 0) {
        outputStream.write(buf,0,len);
      }
      outputStream.close();
      inputStream.close();
      pluginManager.loadPlugin(file);
      Log.i(TAG,"Loaded plugin from assets: " + file);
    }
 catch (    Exception e) {
      e.printStackTrace();
    }
  }
}
