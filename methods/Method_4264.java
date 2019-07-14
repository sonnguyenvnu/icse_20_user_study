/** 
 * Returns the format of audio buffers output by the decoder. Will not be called until the first output buffer has been dequeued, so the decoder may use input data to determine the format. <p> The default implementation returns a 16-bit PCM format with the same channel count and sample rate as the input.
 */
protected Format getOutputFormat(){
  return Format.createAudioSampleFormat(null,MimeTypes.AUDIO_RAW,null,Format.NO_VALUE,Format.NO_VALUE,inputFormat.channelCount,inputFormat.sampleRate,C.ENCODING_PCM_16BIT,null,null,0,null);
}
