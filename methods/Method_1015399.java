protected String[] _list(Object filter){
  Cache<String,ReplCache.Value<Metadata>> internal_cache=cache.getL2Cache();
  Set<String> keys=internal_cache.getInternalMap().keySet();
  if (keys == null)   return null;
  Collection<String> list=new ArrayList<>(keys.size());
  for (  String str : keys) {
    if (isChildOf(getPath(),str)) {
      if (filter instanceof FilenameFilter && !((FilenameFilter)filter).accept(new File(name),filename(str)))       continue;
 else       if (filter instanceof FileFilter && !((FileFilter)filter).accept(new File(str)))       continue;
      list.add(str);
    }
  }
  String[] retval=new String[list.size()];
  int index=0;
  for (  String tmp : list)   retval[index++]=tmp;
  return retval;
}
