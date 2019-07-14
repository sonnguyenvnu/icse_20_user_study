@Override @Nullable protected Checkmark getOldestComputed(){
  if (list.isEmpty())   return null;
  return list.get(list.size() - 1);
}
