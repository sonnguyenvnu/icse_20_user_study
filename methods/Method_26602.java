@Override public boolean contentEquals(@Nullable CharSequence cs){
  return cs != null && contents().contentEquals(cs);
}
