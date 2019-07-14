public int length(){
  if (!justCalc) {
    return isOut ? outbuf.size() : inbuf.available();
  }
  return len;
}
