/** 
 * Skips to the last Ogg page in the stream and reads the header's granule field which is the total number of samples per channel.
 * @param input The {@link ExtractorInput} to read from.
 * @return The total number of samples of this input.
 * @throws IOException If reading from the input fails.
 * @throws InterruptedException If the thread is interrupted.
 */
@VisibleForTesting long readGranuleOfLastPage(ExtractorInput input) throws IOException, InterruptedException {
  skipToNextPage(input);
  pageHeader.reset();
  while ((pageHeader.type & 0x04) != 0x04 && input.getPosition() < endPosition) {
    pageHeader.populate(input,false);
    input.skipFully(pageHeader.headerSize + pageHeader.bodySize);
  }
  return pageHeader.granulePosition;
}
