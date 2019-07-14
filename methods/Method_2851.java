public static String convertId2StringWithIndex(long id){
  String idString=convertId2String(id / MAX_WORDS);
  long index=id % MAX_WORDS;
  return String.format("%s%0" + MAX_INDEX_LENGTH + "d",idString,index);
}
