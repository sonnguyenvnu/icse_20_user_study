@Override public boolean nameEquals(final CharSequence charSequence){
  return caseSensitive ? CharSequenceUtil.equals(name,charSequence) : CharSequenceUtil.equalsIgnoreCase(name,charSequence);
}
