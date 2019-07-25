@Override protected long _timestamp(Date value){
  return (value == null) ? 0L : value.getTime();
}
