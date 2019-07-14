@Override @Nullable protected Checkmark getNewestComputed(){
  if (list.isEmpty())   return null;
  return list.get(0);
}
