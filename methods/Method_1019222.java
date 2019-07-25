public static <R>R get(ValueMetadata entry,RandomAccessFile file){
  try {
    return SerializationUtils.deserialize(SpillableMapUtils.readBytesFromDisk(file,entry.getOffsetOfValue(),entry.getSizeOfValue()));
  }
 catch (  IOException e) {
    throw new HoodieIOException("Unable to readFromDisk Hoodie Record from disk",e);
  }
}
