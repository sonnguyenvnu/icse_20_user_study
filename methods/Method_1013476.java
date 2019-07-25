public static Value make(String str){
  ModelValue mv=(ModelValue)mvTable.get(str);
  if (mv != null)   return mv;
  mv=new ModelValue(str);
  mvTable.put(str,mv);
  return mv;
}
