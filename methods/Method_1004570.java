@Override public void init(File pFile) throws Exception {
  mDatabase=SQLiteDatabase.openDatabase(pFile.getAbsolutePath(),null,SQLiteDatabase.OPEN_READONLY | SQLiteDatabase.NO_LOCALIZED_COLLATORS);
}
