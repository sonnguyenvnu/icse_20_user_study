private static int parseUint8AttributeValue(ParsableByteArray data){
  data.skipBytes(4);
  int atomType=data.readInt();
  if (atomType == Atom.TYPE_data) {
    data.skipBytes(8);
    return data.readUnsignedByte();
  }
  Log.w(TAG,"Failed to parse uint8 attribute value");
  return -1;
}
