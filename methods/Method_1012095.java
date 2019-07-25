public void push(DepLink depLink){
  SetSequence.fromSet(mySeen).addElement(depLink.getRoleModuleKey());
  ListSequence.fromList(myPath).addElement(depLink);
}
