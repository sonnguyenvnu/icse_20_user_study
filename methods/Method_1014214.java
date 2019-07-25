@Override public int[] serialize(){
  serializeHeader(COMMAND_CLASS,COMMAND_METHOD);
  serializeAddress(address);
  serializeBluetoothAddressType(addrType);
  serializeUInt16(connIntervalMin);
  serializeUInt16(connIntervalMax);
  serializeUInt16(timeout);
  serializeUInt16(latency);
  return getPayload();
}
