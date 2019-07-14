public void clear(){
  this.base=new IntArrayList(this.charMap.getInitSize());
  this.check=new IntArrayList(this.charMap.getInitSize());
  this.base.append(0);
  this.check.append(0);
  this.base.append(1);
  this.check.append(0);
  expandArray(this.charMap.getInitSize());
}
