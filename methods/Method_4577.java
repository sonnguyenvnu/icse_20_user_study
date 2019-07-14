void endMasterElement(int id) throws ParserException {
switch (id) {
case ID_SEGMENT_INFO:
    if (timecodeScale == C.TIME_UNSET) {
      timecodeScale=1000000;
    }
  if (durationTimecode != C.TIME_UNSET) {
    durationUs=scaleTimecodeToUs(durationTimecode);
  }
break;
case ID_SEEK:
if (seekEntryId == UNSET_ENTRY_ID || seekEntryPosition == C.POSITION_UNSET) {
throw new ParserException("Mandatory element SeekID or SeekPosition not found");
}
if (seekEntryId == ID_CUES) {
cuesContentPosition=seekEntryPosition;
}
break;
case ID_CUES:
if (!sentSeekMap) {
extractorOutput.seekMap(buildSeekMap());
sentSeekMap=true;
}
 else {
}
break;
case ID_BLOCK_GROUP:
if (blockState != BLOCK_STATE_DATA) {
return;
}
if (!sampleSeenReferenceBlock) {
blockFlags|=C.BUFFER_FLAG_KEY_FRAME;
}
commitSampleToOutput(tracks.get(blockTrackNumber),blockTimeUs);
blockState=BLOCK_STATE_START;
break;
case ID_CONTENT_ENCODING:
if (currentTrack.hasContentEncryption) {
if (currentTrack.cryptoData == null) {
throw new ParserException("Encrypted Track found but ContentEncKeyID was not found");
}
currentTrack.drmInitData=new DrmInitData(new SchemeData(C.UUID_NIL,MimeTypes.VIDEO_WEBM,currentTrack.cryptoData.encryptionKey));
}
break;
case ID_CONTENT_ENCODINGS:
if (currentTrack.hasContentEncryption && currentTrack.sampleStrippedBytes != null) {
throw new ParserException("Combining encryption and compression is not supported");
}
break;
case ID_TRACK_ENTRY:
if (isCodecSupported(currentTrack.codecId)) {
currentTrack.initializeOutput(extractorOutput,currentTrack.number);
tracks.put(currentTrack.number,currentTrack);
}
currentTrack=null;
break;
case ID_TRACKS:
if (tracks.size() == 0) {
throw new ParserException("No valid tracks were found");
}
extractorOutput.endTracks();
break;
default :
break;
}
}
