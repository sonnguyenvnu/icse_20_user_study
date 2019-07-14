private static String buildAudioPropertyString(Format format){
  return format.channelCount == Format.NO_VALUE || format.sampleRate == Format.NO_VALUE ? "" : format.channelCount + "ch, " + format.sampleRate + "Hz";
}
