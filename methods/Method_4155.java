/** 
 * Returns (E-)AC-3 format information given  {@code data} containing a syncframe. The readingposition of  {@code data} will be modified.
 * @param data The data to parse, positioned at the start of the syncframe.
 * @return The (E-)AC-3 format data parsed from the header.
 */
public static SyncFrameInfo parseAc3SyncframeInfo(ParsableBitArray data){
  int initialPosition=data.getPosition();
  data.skipBits(40);
  boolean isEac3=data.readBits(5) == 16;
  data.setPosition(initialPosition);
  String mimeType;
  @StreamType int streamType=SyncFrameInfo.STREAM_TYPE_UNDEFINED;
  int sampleRate;
  int acmod;
  int frameSize;
  int sampleCount;
  boolean lfeon;
  int channelCount;
  if (isEac3) {
    data.skipBits(16);
switch (data.readBits(2)) {
case 0:
      streamType=SyncFrameInfo.STREAM_TYPE_TYPE0;
    break;
case 1:
  streamType=SyncFrameInfo.STREAM_TYPE_TYPE1;
break;
case 2:
streamType=SyncFrameInfo.STREAM_TYPE_TYPE2;
break;
default :
streamType=SyncFrameInfo.STREAM_TYPE_UNDEFINED;
break;
}
data.skipBits(3);
frameSize=(data.readBits(11) + 1) * 2;
int fscod=data.readBits(2);
int audioBlocks;
int numblkscod;
if (fscod == 3) {
numblkscod=3;
sampleRate=SAMPLE_RATE_BY_FSCOD2[data.readBits(2)];
audioBlocks=6;
}
 else {
numblkscod=data.readBits(2);
audioBlocks=BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD[numblkscod];
sampleRate=SAMPLE_RATE_BY_FSCOD[fscod];
}
sampleCount=AUDIO_SAMPLES_PER_AUDIO_BLOCK * audioBlocks;
acmod=data.readBits(3);
lfeon=data.readBit();
channelCount=CHANNEL_COUNT_BY_ACMOD[acmod] + (lfeon ? 1 : 0);
data.skipBits(5 + 5);
if (data.readBit()) {
data.skipBits(8);
}
if (acmod == 0) {
data.skipBits(5);
if (data.readBit()) {
data.skipBits(8);
}
}
if (streamType == SyncFrameInfo.STREAM_TYPE_TYPE1 && data.readBit()) {
data.skipBits(16);
}
if (data.readBit()) {
if (acmod > 2) {
data.skipBits(2);
}
if ((acmod & 0x01) != 0 && acmod > 2) {
data.skipBits(3 + 3);
}
if ((acmod & 0x04) != 0) {
data.skipBits(6);
}
if (lfeon && data.readBit()) {
data.skipBits(5);
}
if (streamType == SyncFrameInfo.STREAM_TYPE_TYPE0) {
if (data.readBit()) {
data.skipBits(6);
}
if (acmod == 0 && data.readBit()) {
data.skipBits(6);
}
if (data.readBit()) {
data.skipBits(6);
}
int mixdef=data.readBits(2);
if (mixdef == 1) {
data.skipBits(1 + 1 + 3);
}
 else if (mixdef == 2) {
data.skipBits(12);
}
 else if (mixdef == 3) {
int mixdeflen=data.readBits(5);
if (data.readBit()) {
data.skipBits(1 + 1 + 3);
if (data.readBit()) {
data.skipBits(4);
}
if (data.readBit()) {
data.skipBits(4);
}
if (data.readBit()) {
data.skipBits(4);
}
if (data.readBit()) {
data.skipBits(4);
}
if (data.readBit()) {
data.skipBits(4);
}
if (data.readBit()) {
data.skipBits(4);
}
if (data.readBit()) {
data.skipBits(4);
}
if (data.readBit()) {
if (data.readBit()) {
data.skipBits(4);
}
if (data.readBit()) {
data.skipBits(4);
}
}
}
if (data.readBit()) {
data.skipBits(5);
if (data.readBit()) {
data.skipBits(5 + 2);
if (data.readBit()) {
data.skipBits(5 + 3);
}
}
}
data.skipBits(8 * (mixdeflen + 2));
data.byteAlign();
}
if (acmod < 2) {
if (data.readBit()) {
data.skipBits(8 + 6);
}
if (acmod == 0) {
if (data.readBit()) {
data.skipBits(8 + 6);
}
}
}
if (data.readBit()) {
if (numblkscod == 0) {
data.skipBits(5);
}
 else {
for (int blk=0; blk < audioBlocks; blk++) {
if (data.readBit()) {
data.skipBits(5);
}
}
}
}
}
}
if (data.readBit()) {
data.skipBits(3 + 1 + 1);
if (acmod == 2) {
data.skipBits(2 + 2);
}
if (acmod >= 6) {
data.skipBits(2);
}
if (data.readBit()) {
data.skipBits(5 + 2 + 1);
}
if (acmod == 0 && data.readBit()) {
data.skipBits(5 + 2 + 1);
}
if (fscod < 3) {
data.skipBit();
}
}
if (streamType == SyncFrameInfo.STREAM_TYPE_TYPE0 && numblkscod != 3) {
data.skipBit();
}
if (streamType == SyncFrameInfo.STREAM_TYPE_TYPE2 && (numblkscod == 3 || data.readBit())) {
data.skipBits(6);
}
mimeType=MimeTypes.AUDIO_E_AC3;
if (data.readBit()) {
int addbsil=data.readBits(6);
if (addbsil == 1 && data.readBits(8) == 1) {
mimeType=MimeTypes.AUDIO_E_AC3_JOC;
}
}
}
 else {
mimeType=MimeTypes.AUDIO_AC3;
data.skipBits(16 + 16);
int fscod=data.readBits(2);
int frmsizecod=data.readBits(6);
frameSize=getAc3SyncframeSize(fscod,frmsizecod);
data.skipBits(5 + 3);
acmod=data.readBits(3);
if ((acmod & 0x01) != 0 && acmod != 1) {
data.skipBits(2);
}
if ((acmod & 0x04) != 0) {
data.skipBits(2);
}
if (acmod == 2) {
data.skipBits(2);
}
sampleRate=SAMPLE_RATE_BY_FSCOD[fscod];
sampleCount=AC3_SYNCFRAME_AUDIO_SAMPLE_COUNT;
lfeon=data.readBit();
channelCount=CHANNEL_COUNT_BY_ACMOD[acmod] + (lfeon ? 1 : 0);
}
return new SyncFrameInfo(mimeType,streamType,channelCount,sampleRate,frameSize,sampleCount);
}
