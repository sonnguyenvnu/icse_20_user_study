@Override protected long _timestamp(java.sql.Date value){
  return (value == null) ? 0L : value.getTime();
}
