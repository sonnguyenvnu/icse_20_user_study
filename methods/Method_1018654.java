/** 
 * @return the file position for a "savefile".
 * @throws PcapNativeException if an error occurs in the pcap native library.
 * @throws NotOpenException if this PcapHandle is not open.
 */
public long ftell() throws PcapNativeException, NotOpenException {
  if (!open) {
    throw new NotOpenException();
  }
  NativeLong nposition;
  if (!dumperLock.readLock().tryLock()) {
    throw new NotOpenException();
  }
  try {
    if (!open) {
      throw new NotOpenException();
    }
    nposition=NativeMappings.pcap_dump_ftell(dumper);
  }
  finally {
    dumperLock.readLock().unlock();
  }
  long position=nposition.longValue();
  if (position < 0) {
    throw new PcapNativeException("Failed to get the file position.");
  }
  return position;
}
