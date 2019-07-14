public void prepare(MediaSource mediaSource,boolean resetPosition,boolean resetState){
  handler.obtainMessage(MSG_PREPARE,resetPosition ? 1 : 0,resetState ? 1 : 0,mediaSource).sendToTarget();
}
