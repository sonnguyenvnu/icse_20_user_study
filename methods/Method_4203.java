@Override public void setAuxEffectInfo(AuxEffectInfo auxEffectInfo){
  if (this.auxEffectInfo.equals(auxEffectInfo)) {
    return;
  }
  int effectId=auxEffectInfo.effectId;
  float sendLevel=auxEffectInfo.sendLevel;
  if (audioTrack != null) {
    if (this.auxEffectInfo.effectId != effectId) {
      audioTrack.attachAuxEffect(effectId);
    }
    if (effectId != AuxEffectInfo.NO_AUX_EFFECT_ID) {
      audioTrack.setAuxEffectSendLevel(sendLevel);
    }
  }
  this.auxEffectInfo=auxEffectInfo;
}
