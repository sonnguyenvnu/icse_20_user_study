private String getLogFilePath(String name){
  Calendar c=Calendar.getInstance();
  return new File(ApplicationLoader.applicationContext.getExternalFilesDir(null),String.format(Locale.US,"logs/%02d_%02d_%04d_%02d_%02d_%02d_%s.txt",c.get(Calendar.DATE),c.get(Calendar.MONTH) + 1,c.get(Calendar.YEAR),c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE),c.get(Calendar.SECOND),name)).getAbsolutePath();
}
