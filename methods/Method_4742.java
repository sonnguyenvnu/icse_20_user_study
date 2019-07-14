/** 
 * Locates the next sample start, advancing the position to the byte that immediately follows identifier. If a sample was not located, the position is advanced to the limit.
 * @param pesBuffer The buffer whose position should be advanced.
 */
private void findNextSample(ParsableByteArray pesBuffer){
  byte[] adtsData=pesBuffer.data;
  int position=pesBuffer.getPosition();
  int endOffset=pesBuffer.limit();
  while (position < endOffset) {
    int data=adtsData[position++] & 0xFF;
    if (matchState == MATCH_STATE_FF && isAdtsSyncBytes((byte)0xFF,(byte)data)) {
      if (foundFirstFrame || checkSyncPositionValid(pesBuffer,position - 2)) {
        currentFrameVersion=(data & 0x8) >> 3;
        hasCrc=(data & 0x1) == 0;
        if (!foundFirstFrame) {
          setCheckingAdtsHeaderState();
        }
 else {
          setReadingAdtsHeaderState();
        }
        pesBuffer.setPosition(position);
        return;
      }
    }
switch (matchState | data) {
case MATCH_STATE_START | 0xFF:
      matchState=MATCH_STATE_FF;
    break;
case MATCH_STATE_START | 'I':
  matchState=MATCH_STATE_I;
break;
case MATCH_STATE_I | 'D':
matchState=MATCH_STATE_ID;
break;
case MATCH_STATE_ID | '3':
setReadingId3HeaderState();
pesBuffer.setPosition(position);
return;
default :
if (matchState != MATCH_STATE_START) {
matchState=MATCH_STATE_START;
position--;
}
break;
}
}
pesBuffer.setPosition(position);
}
