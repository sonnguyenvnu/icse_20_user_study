/** 
 * Reads a TS duration from the input, using the given PCR PID. <p>This reader reads the duration by reading PCR values of the PCR PID packets at the start and at the end of the stream, calculating the difference, and converting that into stream duration.
 * @param input The {@link ExtractorInput} from which data should be read.
 * @param seekPositionHolder If {@link Extractor#RESULT_SEEK} is returned, this holder is updatedto hold the position of the required seek.
 * @param pcrPid The PID of the packet stream within this TS stream that contains PCR values.
 * @return One of the {@code RESULT_} values defined in {@link Extractor}.
 * @throws IOException If an error occurred reading from the input.
 * @throws InterruptedException If the thread was interrupted.
 */
public @Extractor.ReadResult int readDuration(ExtractorInput input,PositionHolder seekPositionHolder,int pcrPid) throws IOException, InterruptedException {
  if (pcrPid <= 0) {
    return finishReadDuration(input);
  }
  if (!isLastPcrValueRead) {
    return readLastPcrValue(input,seekPositionHolder,pcrPid);
  }
  if (lastPcrValue == C.TIME_UNSET) {
    return finishReadDuration(input);
  }
  if (!isFirstPcrValueRead) {
    return readFirstPcrValue(input,seekPositionHolder,pcrPid);
  }
  if (firstPcrValue == C.TIME_UNSET) {
    return finishReadDuration(input);
  }
  long minPcrPositionUs=pcrTimestampAdjuster.adjustTsTimestamp(firstPcrValue);
  long maxPcrPositionUs=pcrTimestampAdjuster.adjustTsTimestamp(lastPcrValue);
  durationUs=maxPcrPositionUs - minPcrPositionUs;
  return finishReadDuration(input);
}
