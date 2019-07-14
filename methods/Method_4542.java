/** 
 * Continues to handle the pending seek operation. Returns one of the  {@code RESULT_} values from{@link Extractor}.
 * @param input The {@link ExtractorInput} from which data should be read.
 * @param seekPositionHolder If {@link Extractor#RESULT_SEEK} is returned, this holder is updatedto hold the position of the required seek.
 * @param outputFrameHolder If {@link Extractor#RESULT_CONTINUE} is returned, this holder may beupdated to hold the extracted frame that contains the target sample. The caller needs to check the byte buffer limit to see if an extracted frame is available.
 * @return One of the {@code RESULT_} values defined in {@link Extractor}.
 * @throws IOException If an error occurred reading from the input.
 * @throws InterruptedException If the thread was interrupted.
 */
public int handlePendingSeek(ExtractorInput input,PositionHolder seekPositionHolder,OutputFrameHolder outputFrameHolder) throws InterruptedException, IOException {
  TimestampSeeker timestampSeeker=Assertions.checkNotNull(this.timestampSeeker);
  while (true) {
    SeekOperationParams seekOperationParams=Assertions.checkNotNull(this.seekOperationParams);
    long floorPosition=seekOperationParams.getFloorBytePosition();
    long ceilingPosition=seekOperationParams.getCeilingBytePosition();
    long searchPosition=seekOperationParams.getNextSearchBytePosition();
    if (ceilingPosition - floorPosition <= minimumSearchRange) {
      markSeekOperationFinished(false,floorPosition);
      return seekToPosition(input,floorPosition,seekPositionHolder);
    }
    if (!skipInputUntilPosition(input,searchPosition)) {
      return seekToPosition(input,searchPosition,seekPositionHolder);
    }
    input.resetPeekPosition();
    TimestampSearchResult timestampSearchResult=timestampSeeker.searchForTimestamp(input,seekOperationParams.getTargetTimePosition(),outputFrameHolder);
switch (timestampSearchResult.type) {
case TimestampSearchResult.TYPE_POSITION_OVERESTIMATED:
      seekOperationParams.updateSeekCeiling(timestampSearchResult.timestampToUpdate,timestampSearchResult.bytePositionToUpdate);
    break;
case TimestampSearchResult.TYPE_POSITION_UNDERESTIMATED:
  seekOperationParams.updateSeekFloor(timestampSearchResult.timestampToUpdate,timestampSearchResult.bytePositionToUpdate);
break;
case TimestampSearchResult.TYPE_TARGET_TIMESTAMP_FOUND:
markSeekOperationFinished(true,timestampSearchResult.bytePositionToUpdate);
skipInputUntilPosition(input,timestampSearchResult.bytePositionToUpdate);
return seekToPosition(input,timestampSearchResult.bytePositionToUpdate,seekPositionHolder);
case TimestampSearchResult.TYPE_NO_TIMESTAMP:
markSeekOperationFinished(false,searchPosition);
return seekToPosition(input,searchPosition,seekPositionHolder);
default :
throw new IllegalStateException("Invalid case");
}
}
}
