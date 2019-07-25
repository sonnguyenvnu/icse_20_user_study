/** 
 * @return the number of records in file plus number of records in buffer
 * @throws IOException
 */
public final synchronized long size() throws IOException {
  return filesize() + this.buffercount;
}
