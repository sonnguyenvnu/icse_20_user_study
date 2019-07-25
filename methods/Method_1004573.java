@Override public void init(File pFile) throws Exception {
  mDatabase=SQLiteDatabase.openDatabase(pFile.getAbsolutePath(),null,SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.OPEN_READONLY);
}
