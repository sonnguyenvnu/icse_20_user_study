@NonNull public static SQLiteDatabase openDatabase(){
  if (opener == null)   throw new IllegalStateException();
  return opener.getWritableDatabase();
}
