@Override public void run(){
  while (!shouldQuit) {
    int eobIndex=dstAudioEncoder.dequeueOutputBuffer(eInfo,WAIT_TIME);
switch (eobIndex) {
case MediaCodec.INFO_OUTPUT_BUFFERS_CHANGED:
      LogTools.d("AudioSenderThread,MediaCodec.INFO_OUTPUT_BUFFERS_CHANGED");
    break;
case MediaCodec.INFO_TRY_AGAIN_LATER:
  break;
case MediaCodec.INFO_OUTPUT_FORMAT_CHANGED:
LogTools.d("AudioSenderThread,MediaCodec.INFO_OUTPUT_FORMAT_CHANGED:" + dstAudioEncoder.getOutputFormat().toString());
ByteBuffer csd0=dstAudioEncoder.getOutputFormat().getByteBuffer("csd-0");
sendAudioSpecificConfig(0,csd0);
break;
default :
LogTools.d("AudioSenderThread,MediaCode,eobIndex=" + eobIndex);
if (startTime == 0) {
startTime=eInfo.presentationTimeUs / 1000;
}
if (eInfo.flags != MediaCodec.BUFFER_FLAG_CODEC_CONFIG && eInfo.size != 0) {
ByteBuffer realData=dstAudioEncoder.getOutputBuffers()[eobIndex];
realData.position(eInfo.offset);
realData.limit(eInfo.offset + eInfo.size);
sendRealData((eInfo.presentationTimeUs / 1000) - startTime,realData);
}
dstAudioEncoder.releaseOutputBuffer(eobIndex,false);
break;
}
}
eInfo=null;
}
