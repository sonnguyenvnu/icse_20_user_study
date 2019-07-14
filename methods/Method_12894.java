public List<String> getHeadByRowNum(int rowNum){
  List<String> l=new ArrayList<String>(head.size());
  for (  List<String> list : head) {
    if (list.size() > rowNum) {
      l.add(list.get(rowNum));
    }
 else {
      l.add(list.get(list.size() - 1));
    }
  }
  return l;
}
