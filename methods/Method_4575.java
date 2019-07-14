/** 
 * Reads and returns an integer of length  {@code byteLength} from the {@link ExtractorInput}.
 * @param input The {@link ExtractorInput} from which to read.
 * @param byteLength The length of the integer being read.
 * @return The read integer value.
 * @throws IOException If an error occurs reading from the input.
 * @throws InterruptedException If the thread is interrupted.
 */
private long readInteger(ExtractorInput input,int byteLength) throws IOException, InterruptedException {
  input.readFully(scratch,0,byteLength);
  long value=0;
  for (int i=0; i < byteLength; i++) {
    value=(value << 8) | (scratch[i] & 0xFF);
  }
  return value;
}
