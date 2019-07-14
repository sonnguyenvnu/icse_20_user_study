public void writeBool(boolean value){
  if (!justCalc) {
    if (value) {
      writeInt32(0x997275b5);
    }
 else {
      writeInt32(0xbc799737);
    }
  }
 else {
    len+=4;
  }
}
