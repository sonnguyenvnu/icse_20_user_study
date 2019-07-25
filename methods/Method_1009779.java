@Override public boolean prepare(RESConfig resConfig){
synchronized (syncOp) {
    resCoreParameters.renderingMode=resConfig.getRenderingMode();
    resCoreParameters.mediacdoecAVCBitRate=resConfig.getBitRate();
    resCoreParameters.videoBufferQueueNum=resConfig.getVideoBufferQueueNum();
    resCoreParameters.mediacodecAVCIFrameInterval=resConfig.getVideoGOP();
    resCoreParameters.mediacodecAVCFrameRate=resCoreParameters.videoFPS;
    loopingInterval=1000 / resCoreParameters.videoFPS;
    dstVideoFormat=new MediaFormat();
    videoGLHandlerThread=new HandlerThread("GLThread");
    videoGLHandlerThread.start();
    videoGLHander=new VideoGLHandler(videoGLHandlerThread.getLooper());
    videoGLHander.sendEmptyMessage(VideoGLHandler.WHAT_INIT);
    return true;
  }
}
