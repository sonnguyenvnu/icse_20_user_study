public Slice encode(){
  Slice slice=Slices.allocate(userKey.length() + SIZE_OF_LONG);
  SliceOutput sliceOutput=slice.output();
  sliceOutput.writeBytes(userKey);
  sliceOutput.writeLong(SequenceNumber.packSequenceAndValueType(sequenceNumber,valueType));
  return slice;
}
