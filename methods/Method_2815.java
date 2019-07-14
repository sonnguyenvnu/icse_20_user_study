public static boolean saveMapToTxt(Map<Object,Object> map,String path,String separator){
  map=new TreeMap<Object,Object>(map);
  return saveEntrySetToTxt(map.entrySet(),path,separator);
}
