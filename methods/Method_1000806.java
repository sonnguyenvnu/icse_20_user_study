@SuppressWarnings("unchecked") public List<T> gets(){
  List<T> aList=new ArrayList<T>(list.size());
  for (  Plugin plugin : list)   if (plugin.canWork())   aList.add((T)plugin);
  return aList;
}
