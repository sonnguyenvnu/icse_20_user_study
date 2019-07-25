void increment(){
  if (sequence < 0xffffffffl) {
    ++sequence;
  }
 else {
    sequence=0;
  }
  isSentToServer=false;
}
