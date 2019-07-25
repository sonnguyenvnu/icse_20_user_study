private static void add(String tla,String tex,int stype,int atype){
  builtInHashTable.put(tla,new Symbol(tla,tex,stype,atype));
  pcalBuiltInHashTable.put(tla,new Symbol(tla,tex,stype,atype));
}
