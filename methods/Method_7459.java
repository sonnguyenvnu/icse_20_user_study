short value16(int offset){
  if (offset + 2 <= data.length) {
    buffer.position(offset);
    return buffer.getShort();
  }
 else {
    return 0;
  }
}
