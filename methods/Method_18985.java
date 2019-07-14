@Override public void deleteRange(int index,int count){
  dispatchLastEvent();
  mTarget.deleteRange(index,count);
}
