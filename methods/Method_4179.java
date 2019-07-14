private void updateState(@State int state){
  this.state=state;
switch (state) {
case STATE_INITIALIZING:
    lastTimestampSampleTimeUs=0;
  initialTimestampPositionFrames=C.POSITION_UNSET;
initializeSystemTimeUs=System.nanoTime() / 1000;
sampleIntervalUs=FAST_POLL_INTERVAL_US;
break;
case STATE_TIMESTAMP:
sampleIntervalUs=FAST_POLL_INTERVAL_US;
break;
case STATE_TIMESTAMP_ADVANCING:
case STATE_NO_TIMESTAMP:
sampleIntervalUs=SLOW_POLL_INTERVAL_US;
break;
case STATE_ERROR:
sampleIntervalUs=ERROR_POLL_INTERVAL_US;
break;
default :
throw new IllegalStateException();
}
}
