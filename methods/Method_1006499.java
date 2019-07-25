void wrap(RunContainer p){
  parent=p;
  pos=parent.nbrruns - 1;
  le=0;
  if (pos >= 0) {
    maxlength=toIntUnsigned(parent.getLength(pos));
    base=toIntUnsigned(parent.getValue(pos));
  }
}
