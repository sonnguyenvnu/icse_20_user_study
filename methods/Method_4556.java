@Override protected boolean parseHeader(ParsableByteArray data) throws UnsupportedFormatException {
  if (!hasParsedAudioDataHeader) {
    int header=data.readUnsignedByte();
    audioFormat=(header >> 4) & 0x0F;
    if (audioFormat == AUDIO_FORMAT_MP3) {
      int sampleRateIndex=(header >> 2) & 0x03;
      int sampleRate=AUDIO_SAMPLING_RATE_TABLE[sampleRateIndex];
      Format format=Format.createAudioSampleFormat(null,MimeTypes.AUDIO_MPEG,null,Format.NO_VALUE,Format.NO_VALUE,1,sampleRate,null,null,0,null);
      output.format(format);
      hasOutputFormat=true;
    }
 else     if (audioFormat == AUDIO_FORMAT_ALAW || audioFormat == AUDIO_FORMAT_ULAW) {
      String type=audioFormat == AUDIO_FORMAT_ALAW ? MimeTypes.AUDIO_ALAW : MimeTypes.AUDIO_MLAW;
      int pcmEncoding=(header & 0x01) == 1 ? C.ENCODING_PCM_16BIT : C.ENCODING_PCM_8BIT;
      Format format=Format.createAudioSampleFormat(null,type,null,Format.NO_VALUE,Format.NO_VALUE,1,8000,pcmEncoding,null,null,0,null);
      output.format(format);
      hasOutputFormat=true;
    }
 else     if (audioFormat != AUDIO_FORMAT_AAC) {
      throw new UnsupportedFormatException("Audio format not supported: " + audioFormat);
    }
    hasParsedAudioDataHeader=true;
  }
 else {
    data.skipBytes(1);
  }
  return true;
}
