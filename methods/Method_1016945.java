public boolean contains(QueueElement e){
  int pos=e.getPosition();
  return pos >= 0 && pos < size && e == elts[pos];
}
