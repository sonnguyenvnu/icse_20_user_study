@Override protected long _timestamp(Calendar value){
  return (value == null) ? 0L : value.getTimeInMillis();
}
