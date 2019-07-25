@Override public boolean prepare(RESConfig resConfig){
synchronized (syncOp) {
    resCoreParameters.renderingMode=resConfig.getRenderingMode();
    resCoreParameters.mediacdoecAVCBitRate=resConfig.getBitRate();
    resCoreParameters.videoBufferQueueNum=resConfig.getVideoBufferQueueNum();
    resCoreParameters.mediacodecAVCIFrameInterval=resConfig.getVideoGOP();
    resCoreParameters.mediacodecAVCFrameRate=resCoreParameters.videoFPS;
    loopingInterval=1000 / resCoreParameters.videoFPS;
    dstVideoFormat=new MediaFormat();
synchronized (syncDstVideoEncoder) {
      dstVideoEncoder=MediaCodecHelper.createSoftVideoMediaCodec(resCoreParameters,dstVideoFormat);
      isEncoderStarted=false;
      if (dstVideoEncoder == null) {
        LogTools.e("create Video MediaCodec failed");
        return false;
      }
    }
    resCoreParameters.previewBufferSize=BuffSizeCalculator.calculator(resCoreParameters.videoWidth,resCoreParameters.videoHeight,resCoreParameters.previewColorFormat);
    int videoWidth=resCoreParameters.videoWidth;
    int videoHeight=resCoreParameters.videoHeight;
    int videoQueueNum=resCoreParameters.videoBufferQueueNum;
    orignVideoBuffs=new RESVideoBuff[videoQueueNum];
    for (int i=0; i < videoQueueNum; i++) {
      orignVideoBuffs[i]=new RESVideoBuff(resCoreParameters.previewColorFormat,resCoreParameters.previewBufferSize);
    }
    lastVideoQueueBuffIndex=0;
    orignNV21VideoBuff=new RESVideoBuff(MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV420SemiPlanar,BuffSizeCalculator.calculator(videoWidth,videoHeight,MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV420SemiPlanar));
    filteredNV21VideoBuff=new RESVideoBuff(MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV420SemiPlanar,BuffSizeCalculator.calculator(videoWidth,videoHeight,MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV420SemiPlanar));
    suitable4VideoEncoderBuff=new RESVideoBuff(resCoreParameters.mediacodecAVCColorFormat,BuffSizeCalculator.calculator(videoWidth,videoHeight,resCoreParameters.mediacodecAVCColorFormat));
    videoFilterHandlerThread=new HandlerThread("videoFilterHandlerThread");
    videoFilterHandlerThread.start();
    videoFilterHandler=new VideoFilterHandler(videoFilterHandlerThread.getLooper());
    return true;
  }
}
