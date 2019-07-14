/** 
 * Convert a row value from the public external coordinate system to our internal private coordinate system. <pre> - External coordinate system: -mActiveTranscriptRows to mScreenRows-1, with the screen being 0..mScreenRows-1. - Internal coordinate system: the mScreenRows lines starting at mScreenFirstRow comprise the screen, while the mActiveTranscriptRows lines ending at mScreenFirstRow-1 form the transcript (as a circular buffer). External ? Internal: [ ...                            ]     [ ...                                     ] [ -mActiveTranscriptRows         ]     [ mScreenFirstRow - mActiveTranscriptRows ] [ ...                            ]     [ ...                                     ] [ 0 (visible screen starts here) ]  ?  [ mScreenFirstRow                         ] [ ...                            ]     [ ...                                     ] [ mScreenRows-1                  ]     [ mScreenFirstRow + mScreenRows-1         ] </pre>
 * @param externalRow a row in the external coordinate system.
 * @return The row corresponding to the input argument in the private coordinate system.
 */
public int externalToInternalRow(int externalRow){
  if (externalRow < -mActiveTranscriptRows || externalRow > mScreenRows)   throw new IllegalArgumentException("extRow=" + externalRow + ", mScreenRows=" + mScreenRows + ", mActiveTranscriptRows=" + mActiveTranscriptRows);
  final int internalRow=mScreenFirstRow + externalRow;
  return (internalRow < 0) ? (mTotalRows + internalRow) : (internalRow % mTotalRows);
}
