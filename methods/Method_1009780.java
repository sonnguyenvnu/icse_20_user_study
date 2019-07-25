public boolean prepare(RESConfig resConfig){
synchronized (syncOp) {
    resCoreParameters.mediacodecAACProfile=MediaCodecInfo.CodecProfileLevel.AACObjectLC;
    resCoreParameters.mediacodecAACSampleRate=44100;
    resCoreParameters.mediacodecAACChannelCount=1;
    resCoreParameters.mediacodecAACBitRate=32 * 1024;
    resCoreParameters.mediacodecAACMaxInputSize=8820;
    dstAudioFormat=new MediaFormat();
    dstAudioEncoder=MediaCodecHelper.createAudioMediaCodec(resCoreParameters,dstAudioFormat);
    if (dstAudioEncoder == null) {
      LogTools.e("create Audio MediaCodec failed");
      return false;
    }
    int audioQueueNum=resCoreParameters.audioBufferQueueNum;
    int orignAudioBuffSize=resCoreParameters.mediacodecAACSampleRate / 5;
    orignAudioBuffs=new RESAudioBuff[audioQueueNum];
    for (int i=0; i < audioQueueNum; i++) {
      orignAudioBuffs[i]=new RESAudioBuff(AudioFormat.ENCODING_PCM_16BIT,orignAudioBuffSize);
    }
    orignAudioBuff=new RESAudioBuff(AudioFormat.ENCODING_PCM_16BIT,orignAudioBuffSize);
    filteredAudioBuff=new RESAudioBuff(AudioFormat.ENCODING_PCM_16BIT,orignAudioBuffSize);
    return true;
  }
}
