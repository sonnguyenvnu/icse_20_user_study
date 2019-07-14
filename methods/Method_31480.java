@Override protected SQLiteConnection doGetConnection(Connection connection){
  return new SQLiteConnection(this,connection);
}
