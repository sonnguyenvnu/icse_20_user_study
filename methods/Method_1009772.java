/** 
 * prepare to stream
 * @param resConfig config
 * @return true if prepare success
 */
public boolean prepare(RESConfig resConfig){
synchronized (SyncOp) {
    checkDirection(resConfig);
    coreParameters.filterMode=resConfig.getFilterMode();
    coreParameters.rtmpAddr=resConfig.getRtmpAddr();
    coreParameters.printDetailMsg=resConfig.isPrintDetailMsg();
    coreParameters.senderQueueLength=200;
    videoClient=new RESVideoClient(coreParameters);
    audioClient=new RESAudioClient(coreParameters);
    if (!videoClient.prepare(resConfig)) {
      LogTools.d("!!!!!videoClient.prepare()failed");
      LogTools.d(coreParameters.toString());
      return false;
    }
    if (!audioClient.prepare(resConfig)) {
      LogTools.d("!!!!!audioClient.prepare()failed");
      LogTools.d(coreParameters.toString());
      return false;
    }
    rtmpSender=new RESRtmpSender();
    rtmpSender.prepare(coreParameters);
    dataCollecter=new RESFlvDataCollecter(){
      @Override public void collect(      RESFlvData flvData,      int type){
        if (rtmpSender != null) {
          rtmpSender.feed(flvData,type);
        }
      }
    }
;
    coreParameters.done=true;
    LogTools.d("===INFO===coreParametersReady:");
    LogTools.d(coreParameters.toString());
    return true;
  }
}
