public void push(FileSnapshot fileSnapshot){
  if (fileSnapshot == null)   return;
  Node fileNode=new Node();
  fileNode.fileSnapshot=fileSnapshot;
  fileNode.next=node;
  node=fileNode;
  ++count;
}
