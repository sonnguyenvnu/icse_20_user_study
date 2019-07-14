private AudioProcessor[] getAvailableAudioProcessors(){
  return shouldConvertHighResIntPcmToFloat ? toFloatPcmAvailableAudioProcessors : toIntPcmAvailableAudioProcessors;
}
