public int length(){
  if (timestampsWithAddresses.get(timestampsWithAddresses.size() - 1).timestamp == null) {
    return timestampsWithAddresses.size() * INT_SIZE_IN_BYTES * 2 - INT_SIZE_IN_BYTES;
  }
 else {
    return timestampsWithAddresses.size() * INT_SIZE_IN_BYTES * 2;
  }
}
