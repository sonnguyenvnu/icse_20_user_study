private void setup(DynamicConfig config){
  int segmentScale=config.getInt(SEGMENT_SCALE_MIN,DEFAULT_SEGMENT_SCALE_IN_MIN);
  validateArguments((segmentScale >= 5) && (segmentScale <= 60),"segment scale in [5, 60] min");
  int inAdvanceLoadMin=config.getInt(LOAD_IN_ADVANCE_MIN,(segmentScale + 1) / 2);
  validateArguments((inAdvanceLoadMin >= 1) && (inAdvanceLoadMin <= ((segmentScale + 1) / 2)),"load in advance time in [1, segmentScale/2] min");
  int loadBlockingExitSec=config.getInt(LOAD_BLOCKING_EXIT_SEC,SEC_PER_MINUTE * (inAdvanceLoadMin + 2) / 3);
  int inAdvanceLoadSec=inAdvanceLoadMin * SEC_PER_MINUTE;
  int loadBlockExitFront=inAdvanceLoadSec / 3;
  int loadBlockExitRear=inAdvanceLoadSec / 2;
  validateArguments((loadBlockingExitSec >= loadBlockExitFront) && (loadBlockingExitSec <= loadBlockExitRear),"load exit block exit time in [inAdvanceLoadMin/3,inAdvanceLoadMin/2] sec. note.");
  this.segmentScale=segmentScale;
  this.inAdvanceLoadMillis=inAdvanceLoadMin * MS_PER_MINUTE;
  this.loadBlockingExitMillis=loadBlockingExitSec * MS_PER_SECONDS;
}
