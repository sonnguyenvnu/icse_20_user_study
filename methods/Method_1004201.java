/** 
 * @see net.openhft.chronicle.bytes.MappedFile#acquireByteStore(long,MappedBytesStoreFactory)
 */
private NativeBytesStore map(long mapSize,long mappingOffsetInFile) throws IOException {
  mapSize=pageAlign(mapSize);
  long minFileSize=mappingOffsetInFile + mapSize;
  FileChannel fileChannel=raf.getChannel();
  if (fileChannel.size() < minFileSize) {
    raf.setLength(minFileSize);
    if (OS.isLinux()) {
      PosixFallocate.fallocate(raf.getFD(),0,minFileSize);
    }
  }
  long address=OS.map(fileChannel,READ_WRITE,mappingOffsetInFile,mapSize);
  resources.addMemoryResource(address,mapSize);
  return new NativeBytesStore(address,mapSize,null,false);
}
