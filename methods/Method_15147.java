private static CityDB openCityDB(Context context,String packageName){
  String path="/data" + Environment.getDataDirectory().getAbsolutePath() + File.separator + packageName + File.separator + CityDB.CITY_DB_NAME;
  File db=new File(path);
  if (!db.exists()) {
    try {
      InputStream is=context.getAssets().open("city.db");
      FileOutputStream fos=new FileOutputStream(db);
      int len=-1;
      byte[] buffer=new byte[1024];
      while ((len=is.read(buffer)) != -1) {
        fos.write(buffer,0,len);
        fos.flush();
      }
      fos.close();
      is.close();
    }
 catch (    IOException e) {
      e.printStackTrace();
      System.exit(0);
    }
  }
  return new CityDB(context,path);
}
