public void start(RESFlvDataCollecter flvDataCollecter){
synchronized (syncOp) {
    try {
      for (      RESAudioBuff buff : orignAudioBuffs) {
        buff.isReadyToFill=true;
      }
      if (dstAudioEncoder == null) {
        dstAudioEncoder=MediaCodec.createEncoderByType(dstAudioFormat.getString(MediaFormat.KEY_MIME));
      }
      dstAudioEncoder.configure(dstAudioFormat,null,null,MediaCodec.CONFIGURE_FLAG_ENCODE);
      dstAudioEncoder.start();
      lastAudioQueueBuffIndex=0;
      audioFilterHandlerThread=new HandlerThread("audioFilterHandlerThread");
      audioSenderThread=new AudioSenderThread("AudioSenderThread",dstAudioEncoder,flvDataCollecter);
      audioFilterHandlerThread.start();
      audioSenderThread.start();
      audioFilterHandler=new AudioFilterHandler(audioFilterHandlerThread.getLooper());
    }
 catch (    Exception e) {
      LogTools.trace("RESSoftAudioCore",e);
    }
  }
}
