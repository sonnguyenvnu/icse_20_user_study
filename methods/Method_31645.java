/** 
 * Calculates the checksum of this resource. The checksum is encoding and line-ending independent.
 * @return The crc-32 checksum of the bytes.
 */
public final int checksum(){
  if (checksum == null) {
    final CRC32 crc32=new CRC32();
    BufferedReader reader=null;
    try {
      reader=new BufferedReader(read(),4096);
      String line;
      while ((line=reader.readLine()) != null) {
        crc32.update(StringUtils.trimLineBreak(line).getBytes(StandardCharsets.UTF_8));
      }
    }
 catch (    IOException e) {
      throw new FlywayException("Unable to calculate checksum for " + getFilename() + ": " + e.getMessage(),e);
    }
 finally {
      IOUtils.close(reader);
    }
    checksum=(int)crc32.getValue();
  }
  return checksum;
}
