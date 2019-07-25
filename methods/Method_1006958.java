/** 
 * If  {@link CheckpointAlgorithm#isReadyToCheckpoint()} is truewe will call  {@link CheckpointAlgorithm#endCheckpoint()}
 * @param context a {@link RepeatContext}
 */
@Override public void update(RepeatContext context){
  try {
    if (isComplete) {
      policy.endCheckpoint();
    }
  }
 catch (  Exception e) {
    throw new BatchRuntimeException(e);
  }
}
