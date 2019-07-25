private void grow(){
  UniqueString[] old=this.table;
  this.count=0;
  this.length=2 * this.length + 1;
  this.thresh=this.length / 2;
  this.table=new UniqueString[this.length];
  for (int i=0; i < old.length; i++) {
    UniqueString var=old[i];
    if (var != null)     this.put(var);
  }
}
