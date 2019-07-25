void wrap(RunContainer p){
  parent=p;
  pos=0;
  le=0;
  if (pos < parent.nbrruns) {
    maxlength=toIntUnsigned(parent.getLength(pos));
    base=toIntUnsigned(parent.getValue(pos));
  }
}
