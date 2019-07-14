@Nullable @Override protected Score getOldestComputed(){
  if (list.isEmpty())   return null;
  return list.get(list.size() - 1);
}
