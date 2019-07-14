public void push(FileTreeSnapshot snapshot){
  Node node=new Node();
  node.snapshot=snapshot;
  node.next=mFirstNode;
  mFirstNode=node;
  mSize++;
}
