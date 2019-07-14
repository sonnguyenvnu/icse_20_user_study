private void outputFormat(FlacStreamInfo streamInfo){
  Format mediaFormat=Format.createAudioSampleFormat(null,MimeTypes.AUDIO_RAW,null,streamInfo.bitRate(),streamInfo.maxDecodedFrameSize(),streamInfo.channels,streamInfo.sampleRate,getPcmEncoding(streamInfo.bitsPerSample),0,0,null,null,0,null,isId3MetadataDisabled ? null : id3Metadata);
  trackOutput.format(mediaFormat);
}
