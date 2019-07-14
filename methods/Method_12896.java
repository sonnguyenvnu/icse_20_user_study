public int getRowNum(){
  int headRowNum=0;
  for (  List<String> list : head) {
    if (list != null && list.size() > 0) {
      if (list.size() > headRowNum) {
        headRowNum=list.size();
      }
    }
  }
  return headRowNum;
}
