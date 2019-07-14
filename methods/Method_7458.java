int value32(int offset){
  if (offset + 4 <= data.length) {
    buffer.position(offset);
    return buffer.getInt();
  }
 else {
    return 0;
  }
}
