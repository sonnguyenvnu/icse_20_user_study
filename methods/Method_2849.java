public static long convertString2IdWithIndex(String idString,long index){
  long id=convertString2Id(idString);
  id=id * MAX_WORDS + index;
  return id;
}
