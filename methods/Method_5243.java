public void updateEventStream(EventStream eventStream,boolean eventStreamAppendable){
  long lastReadPositionUs=currentIndex == 0 ? C.TIME_UNSET : eventTimesUs[currentIndex - 1];
  this.eventStreamAppendable=eventStreamAppendable;
  this.eventStream=eventStream;
  this.eventTimesUs=eventStream.presentationTimesUs;
  if (pendingSeekPositionUs != C.TIME_UNSET) {
    seekToUs(pendingSeekPositionUs);
  }
 else   if (lastReadPositionUs != C.TIME_UNSET) {
    currentIndex=Util.binarySearchCeil(eventTimesUs,lastReadPositionUs,false,false);
  }
}
