@Override public void finished(){
  checkState(sizeHot + sizeCold + sizeTest == data.size());
  checkState(sizeHot + sizeCold <= maximumSize);
  checkState(maximumColdSize <= maximumSize);
}
