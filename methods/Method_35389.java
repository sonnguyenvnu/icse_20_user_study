public FileTreeSnapshot pop(){
  if (mFirstNode == null)   return null;
  FileTreeSnapshot snapshot=mFirstNode.snapshot;
  mFirstNode=mFirstNode.next;
  mSize--;
  return snapshot;
}
