public long getNumberOfAssignedPages(){
  if (inProcessPages != null) {
    return inProcessPages.getLength();
  }
 else {
    return 0;
  }
}
