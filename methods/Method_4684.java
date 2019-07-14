/** 
 * Skips to the position of the start of the page containing the  {@code targetGranule} and returnsthe granule of the page previous to the target page.
 * @param input the {@link ExtractorInput} to read from.
 * @param targetGranule the target granule.
 * @param currentGranule the current granule or -1 if it's unknown.
 * @return the granule of the prior page or the {@code currentGranule} if there isn't a priorpage.
 * @throws ParserException thrown if populating the page header fails.
 * @throws IOException thrown if reading from the input fails.
 * @throws InterruptedException thrown if interrupted while reading from the input.
 */
@VisibleForTesting long skipToPageOfGranule(ExtractorInput input,long targetGranule,long currentGranule) throws IOException, InterruptedException {
  pageHeader.populate(input,false);
  while (pageHeader.granulePosition < targetGranule) {
    input.skipFully(pageHeader.headerSize + pageHeader.bodySize);
    currentGranule=pageHeader.granulePosition;
    pageHeader.populate(input,false);
  }
  input.resetPeekPosition();
  return currentGranule;
}
