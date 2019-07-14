/** 
 * Skips over data to reach the next tag header.
 * @param input The {@link ExtractorInput} from which to read.
 * @throws IOException If an error occurred skipping data from the source.
 * @throws InterruptedException If the thread was interrupted.
 */
private void skipToTagHeader(ExtractorInput input) throws IOException, InterruptedException {
  input.skipFully(bytesToNextTagHeader);
  bytesToNextTagHeader=0;
  state=STATE_READING_TAG_HEADER;
}
