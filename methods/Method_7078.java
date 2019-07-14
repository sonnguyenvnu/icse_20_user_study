public static void close(Cursor cursor){
  if (cursor != null && !cursor.isClosed()) {
    cursor.close();
  }
}
