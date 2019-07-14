@Override public long read(ExtractorInput input) throws IOException, InterruptedException {
switch (state) {
case STATE_IDLE:
    return -1;
case STATE_SEEK_TO_END:
  positionBeforeSeekToEnd=input.getPosition();
state=STATE_READ_LAST_PAGE;
long lastPageSearchPosition=endPosition - OggPageHeader.MAX_PAGE_SIZE;
if (lastPageSearchPosition > positionBeforeSeekToEnd) {
return lastPageSearchPosition;
}
case STATE_READ_LAST_PAGE:
totalGranules=readGranuleOfLastPage(input);
state=STATE_IDLE;
return positionBeforeSeekToEnd;
case STATE_SEEK:
long currentGranule;
if (targetGranule == 0) {
currentGranule=0;
}
 else {
long position=getNextSeekPosition(targetGranule,input);
if (position >= 0) {
return position;
}
currentGranule=skipToPageOfGranule(input,targetGranule,-(position + 2));
}
state=STATE_IDLE;
return -(currentGranule + 2);
default :
throw new IllegalStateException();
}
}
