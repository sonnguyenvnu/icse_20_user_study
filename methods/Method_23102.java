/** 
 * Writes a 32-bit Mac timestamp (seconds since 1902).
 * @param date
 * @throws java.io.IOException
 */
public void writeMacTimestamp(Date date) throws IOException {
  long millis=date.getTime();
  long qtMillis=millis - MAC_TIMESTAMP_EPOCH;
  long qtSeconds=qtMillis / 1000;
  writeUInt(qtSeconds);
}
