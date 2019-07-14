/** 
 * Adds an audio track to the movie, and configures using an {@code AudioFormat} object from the javax.sound API.<p> Use this method for writing audio data from an  {@code AudioInputStream}into a QuickTime Movie file.
 * @param format The javax.sound audio format.
 */
public void addAudioTrack(AudioFormat format) throws IOException {
  ensureStarted();
  String qtAudioFormat;
  double sampleRate=format.getSampleRate();
  long timeScale=(int)Math.floor(sampleRate);
  int sampleSizeInBits=format.getSampleSizeInBits();
  int numberOfChannels=format.getChannels();
  boolean bigEndian=format.isBigEndian();
  int frameDuration=(int)(format.getSampleRate() / format.getFrameRate());
  int frameSize=format.getFrameSize();
  boolean isCompressed=format.getProperty("vbr") != null && ((Boolean)format.getProperty("vbr")).booleanValue();
  if (format.getEncoding().equals(AudioFormat.Encoding.ALAW)) {
    qtAudioFormat="alaw";
    if (sampleSizeInBits != 8) {
      throw new IllegalArgumentException("Sample size of 8 for ALAW required:" + sampleSizeInBits);
    }
  }
 else   if (format.getEncoding().equals(AudioFormat.Encoding.PCM_SIGNED)) {
switch (sampleSizeInBits) {
case 16:
      qtAudioFormat=(bigEndian) ? "twos" : "sowt";
    break;
case 24:
  qtAudioFormat="in24";
break;
case 32:
qtAudioFormat="in32";
break;
default :
throw new IllegalArgumentException("Sample size of 16, 24 or 32 for PCM_SIGNED required:" + sampleSizeInBits);
}
}
 else if (format.getEncoding().equals(AudioFormat.Encoding.PCM_UNSIGNED)) {
if (sampleSizeInBits != 8) {
throw new IllegalArgumentException("Sample size of 8 PCM_UNSIGNED required:" + sampleSizeInBits);
}
qtAudioFormat="raw ";
}
 else if (format.getEncoding().equals(AudioFormat.Encoding.ULAW)) {
if (sampleSizeInBits != 8) {
throw new IllegalArgumentException("Sample size of 8 for ULAW required:" + sampleSizeInBits);
}
qtAudioFormat="ulaw";
}
 else if (format.getEncoding().toString().equals("MP3")) {
qtAudioFormat=".mp3";
}
 else {
qtAudioFormat=format.getEncoding().toString();
if (qtAudioFormat.length() != 4) {
throw new IllegalArgumentException("Unsupported encoding:" + format.getEncoding());
}
}
addAudioTrack(qtAudioFormat,timeScale,sampleRate,numberOfChannels,sampleSizeInBits,isCompressed,frameDuration,frameSize);
}
