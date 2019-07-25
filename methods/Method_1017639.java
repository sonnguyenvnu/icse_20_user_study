public void append(ResultWrapper newResult){
  ResultWrapper tail=this;
  while (tail.next != null) {
    tail=tail.next;
  }
  tail.next=newResult;
}
