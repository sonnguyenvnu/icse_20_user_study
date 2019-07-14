public int getFreeSize(){
  int count=0;
  int chk=this.check.get(0);
  while (chk != 0) {
    count++;
    chk=this.check.get(-chk);
  }
  return count;
}
