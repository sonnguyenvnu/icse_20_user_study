private static void removeValues(HashMap<String,byte[]> metadata,List<String> names){
  for (int i=0; i < names.size(); i++) {
    metadata.remove(names.get(i));
  }
}
