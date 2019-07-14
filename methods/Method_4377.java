private static boolean containsSchemeDataWithUuid(ArrayList<SchemeData> datas,int limit,UUID uuid){
  for (int i=0; i < limit; i++) {
    if (datas.get(i).uuid.equals(uuid)) {
      return true;
    }
  }
  return false;
}
