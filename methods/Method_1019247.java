Deque<Chain> reverse(){
  Deque<Chain> reverseChain=new ArrayDeque<Chain>(8);
  Chain current=this;
  reverseChain.addFirst(current);
  while (current.hasParent()) {
    current=current.getParent();
    reverseChain.addFirst(current);
  }
  return reverseChain;
}
