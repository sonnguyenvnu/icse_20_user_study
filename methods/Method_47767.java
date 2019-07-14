@SuppressWarnings("ResultOfMethodCallIgnored") public static String saveDatabaseCopy(Context context,File dir) throws IOException {
  SimpleDateFormat dateFormat=DateFormats.getBackupDateFormat();
  String date=dateFormat.format(DateUtils.getLocalTime());
  String format="%s/Loop Habits Backup %s.db";
  String filename=String.format(format,dir.getAbsolutePath(),date);
  File db=getDatabaseFile(context);
  File dbCopy=new File(filename);
  FileUtils.copy(db,dbCopy);
  return dbCopy.getAbsolutePath();
}
