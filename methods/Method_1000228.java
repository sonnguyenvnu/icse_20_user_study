public int book(int start,int end){
  events.add(new Node(start,1));
  events.add(new Node(end,0));
  events.sort((Comparator.comparing(Node::getN).thenComparing(Node::getIndex)));
  int count=0;
  for (  Node node : events) {
    if (node.index == 1 && node.n >= end) {
      break;
    }
    count+=node.index == 1 ? 1 : -1;
    if (node.getN() >= start) {
      max=Math.max(max,count);
    }
  }
  return max;
}
