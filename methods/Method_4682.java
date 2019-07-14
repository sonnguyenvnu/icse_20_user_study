/** 
 * Skips to the next page. Searches for the next page header.
 * @param input The {@code ExtractorInput} to skip to the next page.
 * @param limit The limit up to which the search should take place.
 * @return Whether the next page was found.
 * @throws IOException thrown if peeking/reading from the input fails.
 * @throws InterruptedException thrown if interrupted while peeking/reading from the input.
 */
@VisibleForTesting boolean skipToNextPage(ExtractorInput input,long limit) throws IOException, InterruptedException {
  limit=Math.min(limit + 3,endPosition);
  byte[] buffer=new byte[2048];
  int peekLength=buffer.length;
  while (true) {
    if (input.getPosition() + peekLength > limit) {
      peekLength=(int)(limit - input.getPosition());
      if (peekLength < 4) {
        return false;
      }
    }
    input.peekFully(buffer,0,peekLength,false);
    for (int i=0; i < peekLength - 3; i++) {
      if (buffer[i] == 'O' && buffer[i + 1] == 'g' && buffer[i + 2] == 'g' && buffer[i + 3] == 'S') {
        input.skipFully(i);
        return true;
      }
    }
    input.skipFully(peekLength - 3);
  }
}
