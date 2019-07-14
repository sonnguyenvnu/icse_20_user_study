private TrackOutput buildTrackOutput(long subsampleOffsetUs){
  TrackOutput trackOutput=output.track(0,C.TRACK_TYPE_TEXT);
  trackOutput.format(Format.createTextSampleFormat(null,MimeTypes.TEXT_VTT,null,Format.NO_VALUE,0,language,null,subsampleOffsetUs));
  output.endTracks();
  return trackOutput;
}
