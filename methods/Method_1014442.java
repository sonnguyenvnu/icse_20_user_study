@Override public void process(AudioStream audioStream) throws UnsupportedAudioFormatException, UnsupportedAudioStreamException {
  AudioPlayer audioPlayer=new AudioPlayer(audioStream);
  audioPlayer.start();
  try {
    audioPlayer.join();
  }
 catch (  InterruptedException e) {
    logger.error("Playing audio has been interrupted.");
  }
}
