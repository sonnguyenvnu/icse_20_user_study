void addMissing(ArrayList<String> result,long start,long end){
  if (start > end) {
    return;
  }
 else   if (start == end) {
    result.add(start + "");
  }
 else {
    result.add(start + "->" + end);
  }
}
