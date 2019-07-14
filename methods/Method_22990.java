static int findRecord(String path){
  for (int i=0; i < records.size(); i++) {
    if (path.equals(records.get(i).path)) {
      return i;
    }
  }
  return -1;
}
