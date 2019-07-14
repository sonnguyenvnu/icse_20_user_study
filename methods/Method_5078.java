@Override public void setAuxEffectInfo(AuxEffectInfo auxEffectInfo){
  verifyApplicationThread();
  for (  Renderer renderer : renderers) {
    if (renderer.getTrackType() == C.TRACK_TYPE_AUDIO) {
      player.createMessage(renderer).setType(C.MSG_SET_AUX_EFFECT_INFO).setPayload(auxEffectInfo).send();
    }
  }
}
