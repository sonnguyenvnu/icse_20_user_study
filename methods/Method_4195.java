private void setupAudioProcessors(){
  ArrayList<AudioProcessor> newAudioProcessors=new ArrayList<>();
  for (  AudioProcessor audioProcessor : getAvailableAudioProcessors()) {
    if (audioProcessor.isActive()) {
      newAudioProcessors.add(audioProcessor);
    }
 else {
      audioProcessor.flush();
    }
  }
  int count=newAudioProcessors.size();
  activeAudioProcessors=newAudioProcessors.toArray(new AudioProcessor[count]);
  outputBuffers=new ByteBuffer[count];
  flushAudioProcessors();
}
