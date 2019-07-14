@Override public void handleMessage(int messageType,@Nullable Object message) throws ExoPlaybackException {
switch (messageType) {
case C.MSG_SET_VOLUME:
    audioSink.setVolume((Float)message);
  break;
case C.MSG_SET_AUDIO_ATTRIBUTES:
AudioAttributes audioAttributes=(AudioAttributes)message;
audioSink.setAudioAttributes(audioAttributes);
break;
case C.MSG_SET_AUX_EFFECT_INFO:
AuxEffectInfo auxEffectInfo=(AuxEffectInfo)message;
audioSink.setAuxEffectInfo(auxEffectInfo);
break;
default :
super.handleMessage(messageType,message);
break;
}
}
