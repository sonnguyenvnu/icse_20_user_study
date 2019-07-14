public void skip(int count){
  if (count == 0) {
    return;
  }
  if (!justCalc) {
    buffer.position(buffer.position() + count);
  }
 else {
    len+=count;
  }
}
