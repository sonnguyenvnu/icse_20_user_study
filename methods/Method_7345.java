private static boolean isGoodAudioEffect(AudioEffect effect){
  Pattern globalImpl=makeNonEmptyRegex("adsp_good_impls"), globalName=makeNonEmptyRegex("adsp_good_names");
  AudioEffect.Descriptor desc=effect.getDescriptor();
  VLog.d(effect.getClass().getSimpleName() + ": implementor=" + desc.implementor + ", name=" + desc.name);
  if (globalImpl != null && globalImpl.matcher(desc.implementor).find()) {
    return true;
  }
  if (globalName != null && globalName.matcher(desc.name).find()) {
    return true;
  }
  if (effect instanceof AcousticEchoCanceler) {
    Pattern impl=makeNonEmptyRegex("aaec_good_impls"), name=makeNonEmptyRegex("aaec_good_names");
    if (impl != null && impl.matcher(desc.implementor).find())     return true;
    if (name != null && name.matcher(desc.name).find())     return true;
  }
  if (effect instanceof NoiseSuppressor) {
    Pattern impl=makeNonEmptyRegex("ans_good_impls"), name=makeNonEmptyRegex("ans_good_names");
    if (impl != null && impl.matcher(desc.implementor).find())     return true;
    if (name != null && name.matcher(desc.name).find())     return true;
  }
  return false;
}
