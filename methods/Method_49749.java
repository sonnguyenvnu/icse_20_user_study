public static void checkSQLiteException(Context context,SQLiteException e){
  if (isLowMemory(e)) {
    Toast.makeText(context,"Low Memory",Toast.LENGTH_SHORT).show();
  }
 else {
    throw e;
  }
}
