@Nullable @Override protected Score getNewestComputed(){
  if (list.isEmpty())   return null;
  return list.get(0);
}
