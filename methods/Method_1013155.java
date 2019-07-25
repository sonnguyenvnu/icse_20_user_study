/** 
 * Resets the values to defaults
 */
protected void initialize(){
  isDone=false;
  isTLCStarted=false;
  errors=new Vector<TLCError>();
  lastDetectedError=null;
  model.removeMarkers(ModelHelper.TLC_MODEL_ERROR_MARKER_TLC);
  coverageInfo=new CoverageInformation();
  progressInformation=new Vector<StateSpaceInformationItem>();
  startTimestamp=Long.MIN_VALUE;
  finishTimestamp=Long.MIN_VALUE;
  tlcMode="";
  lastCheckpointTimeStamp=Long.MIN_VALUE;
  coverageTimestamp="";
  setCurrentStatus(NOT_RUNNING);
  setFingerprintCollisionProbability("");
  progressOutput=new Document(NO_OUTPUT_AVAILABLE);
  userOutput=new Document(NO_OUTPUT_AVAILABLE);
  constantExprEvalOutput="";
  isSymmetryWithLiveness=false;
}
