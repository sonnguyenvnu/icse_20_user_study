public void pop(){
  DepLink last=ListSequence.fromList(myPath).removeLastElement();
  SetSequence.fromSet(mySeen).removeElement(last.getRoleModuleKey());
}
