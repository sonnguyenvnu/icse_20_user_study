private String toString(Map<String,? extends Object> map){
  if (map.size() == 0) {
    return "";
  }
  return unsortedList(map);
}
