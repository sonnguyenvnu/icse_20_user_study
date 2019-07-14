void integerElement(int id,long value) throws ParserException {
switch (id) {
case ID_EBML_READ_VERSION:
    if (value != 1) {
      throw new ParserException("EBMLReadVersion " + value + " not supported");
    }
  break;
case ID_DOC_TYPE_READ_VERSION:
if (value < 1 || value > 2) {
  throw new ParserException("DocTypeReadVersion " + value + " not supported");
}
break;
case ID_SEEK_POSITION:
seekEntryPosition=value + segmentContentPosition;
break;
case ID_TIMECODE_SCALE:
timecodeScale=value;
break;
case ID_PIXEL_WIDTH:
currentTrack.width=(int)value;
break;
case ID_PIXEL_HEIGHT:
currentTrack.height=(int)value;
break;
case ID_DISPLAY_WIDTH:
currentTrack.displayWidth=(int)value;
break;
case ID_DISPLAY_HEIGHT:
currentTrack.displayHeight=(int)value;
break;
case ID_DISPLAY_UNIT:
currentTrack.displayUnit=(int)value;
break;
case ID_TRACK_NUMBER:
currentTrack.number=(int)value;
break;
case ID_FLAG_DEFAULT:
currentTrack.flagDefault=value == 1;
break;
case ID_FLAG_FORCED:
currentTrack.flagForced=value == 1;
break;
case ID_TRACK_TYPE:
currentTrack.type=(int)value;
break;
case ID_DEFAULT_DURATION:
currentTrack.defaultSampleDurationNs=(int)value;
break;
case ID_CODEC_DELAY:
currentTrack.codecDelayNs=value;
break;
case ID_SEEK_PRE_ROLL:
currentTrack.seekPreRollNs=value;
break;
case ID_CHANNELS:
currentTrack.channelCount=(int)value;
break;
case ID_AUDIO_BIT_DEPTH:
currentTrack.audioBitDepth=(int)value;
break;
case ID_REFERENCE_BLOCK:
sampleSeenReferenceBlock=true;
break;
case ID_CONTENT_ENCODING_ORDER:
if (value != 0) {
throw new ParserException("ContentEncodingOrder " + value + " not supported");
}
break;
case ID_CONTENT_ENCODING_SCOPE:
if (value != 1) {
throw new ParserException("ContentEncodingScope " + value + " not supported");
}
break;
case ID_CONTENT_COMPRESSION_ALGORITHM:
if (value != 3) {
throw new ParserException("ContentCompAlgo " + value + " not supported");
}
break;
case ID_CONTENT_ENCRYPTION_ALGORITHM:
if (value != 5) {
throw new ParserException("ContentEncAlgo " + value + " not supported");
}
break;
case ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE:
if (value != 1) {
throw new ParserException("AESSettingsCipherMode " + value + " not supported");
}
break;
case ID_CUE_TIME:
cueTimesUs.add(scaleTimecodeToUs(value));
break;
case ID_CUE_CLUSTER_POSITION:
if (!seenClusterPositionForCurrentCuePoint) {
cueClusterPositions.add(value);
seenClusterPositionForCurrentCuePoint=true;
}
break;
case ID_TIME_CODE:
clusterTimecodeUs=scaleTimecodeToUs(value);
break;
case ID_BLOCK_DURATION:
blockDurationUs=scaleTimecodeToUs(value);
break;
case ID_STEREO_MODE:
int layout=(int)value;
switch (layout) {
case 0:
currentTrack.stereoMode=C.STEREO_MODE_MONO;
break;
case 1:
currentTrack.stereoMode=C.STEREO_MODE_LEFT_RIGHT;
break;
case 3:
currentTrack.stereoMode=C.STEREO_MODE_TOP_BOTTOM;
break;
case 15:
currentTrack.stereoMode=C.STEREO_MODE_STEREO_MESH;
break;
default :
break;
}
break;
case ID_COLOUR_PRIMARIES:
currentTrack.hasColorInfo=true;
switch ((int)value) {
case 1:
currentTrack.colorSpace=C.COLOR_SPACE_BT709;
break;
case 4:
case 5:
case 6:
case 7:
currentTrack.colorSpace=C.COLOR_SPACE_BT601;
break;
case 9:
currentTrack.colorSpace=C.COLOR_SPACE_BT2020;
break;
default :
break;
}
break;
case ID_COLOUR_TRANSFER:
switch ((int)value) {
case 1:
case 6:
case 7:
currentTrack.colorTransfer=C.COLOR_TRANSFER_SDR;
break;
case 16:
currentTrack.colorTransfer=C.COLOR_TRANSFER_ST2084;
break;
case 18:
currentTrack.colorTransfer=C.COLOR_TRANSFER_HLG;
break;
default :
break;
}
break;
case ID_COLOUR_RANGE:
switch ((int)value) {
case 1:
currentTrack.colorRange=C.COLOR_RANGE_LIMITED;
break;
case 2:
currentTrack.colorRange=C.COLOR_RANGE_FULL;
break;
default :
break;
}
break;
case ID_MAX_CLL:
currentTrack.maxContentLuminance=(int)value;
break;
case ID_MAX_FALL:
currentTrack.maxFrameAverageLuminance=(int)value;
break;
case ID_PROJECTION_TYPE:
switch ((int)value) {
case 0:
currentTrack.projectionType=C.PROJECTION_RECTANGULAR;
break;
case 1:
currentTrack.projectionType=C.PROJECTION_EQUIRECTANGULAR;
break;
case 2:
currentTrack.projectionType=C.PROJECTION_CUBEMAP;
break;
case 3:
currentTrack.projectionType=C.PROJECTION_MESH;
break;
default :
break;
}
break;
default :
break;
}
}
