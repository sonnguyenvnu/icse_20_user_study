protected long getNextRandomId(){
  long val=0;
  while (val == 0) {
    val=Utilities.random.nextLong();
  }
  return val;
}
