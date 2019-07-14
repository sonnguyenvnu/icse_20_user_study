private DiskDumpInfoEntry dumpCacheEntry(Entry entry) throws IOException {
  EntryImpl entryImpl=(EntryImpl)entry;
  String firstBits="";
  byte[] bytes=entryImpl.getResource().read();
  String type=typeOfBytes(bytes);
  if (type.equals("undefined") && bytes.length >= 4) {
    firstBits=String.format((Locale)null,"0x%02X 0x%02X 0x%02X 0x%02X",bytes[0],bytes[1],bytes[2],bytes[3]);
  }
  String path=entryImpl.getResource().getFile().getPath();
  return new DiskDumpInfoEntry(path,type,entryImpl.getSize(),firstBits);
}
