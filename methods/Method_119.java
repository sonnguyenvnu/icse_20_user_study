public void moveDisks(int n,Tower destination,Tower buffer){
  if (n > 0) {
    moveDisks(n - 1,buffer,destination);
    moveTopTo(destination);
    buffer.moveDisks(n - 1,destination,this);
  }
}
