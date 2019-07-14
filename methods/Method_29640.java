public int getNumberOfPages(){
  int totalPageCount=getTotalPages();
  return totalPageCount >= 10 ? 10 : totalPageCount;
}
