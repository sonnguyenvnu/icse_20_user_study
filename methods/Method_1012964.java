public FileSnapshot pop(){
  Node fileNode=node;
  if (fileNode == null)   return null;
  FileSnapshot fileSnapshot=fileNode.fileSnapshot;
  node=fileNode.next;
  --count;
  return fileSnapshot;
}
