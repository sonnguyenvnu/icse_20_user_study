public Node peek(){
  final Node tail=this.lpConsumerNode();
  if (tail == stub) {
    return tail.getNext();
  }
 else {
    return tail;
  }
}
