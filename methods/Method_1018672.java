public int length(){
  if (address == null) {
    return 0;
  }
 else {
    return INET4_ADDRESS_SIZE_IN_BYTES + timestamps.size() * INT_SIZE_IN_BYTES;
  }
}
