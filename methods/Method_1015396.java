public boolean mkdir(){
  try {
    boolean parents_exist=checkParentDirs(getPath(),false);
    if (!parents_exist)     return false;
    cache.put(getPath(),new Metadata(0,System.currentTimeMillis(),chunk_size,Metadata.DIR),(short)-1,0,true);
    return true;
  }
 catch (  IOException e) {
    e.printStackTrace();
    return false;
  }
}
