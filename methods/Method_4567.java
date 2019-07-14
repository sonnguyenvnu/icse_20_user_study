@Override protected boolean parseHeader(ParsableByteArray data) throws UnsupportedFormatException {
  int header=data.readUnsignedByte();
  int frameType=(header >> 4) & 0x0F;
  int videoCodec=(header & 0x0F);
  if (videoCodec != VIDEO_CODEC_AVC) {
    throw new UnsupportedFormatException("Video format not supported: " + videoCodec);
  }
  this.frameType=frameType;
  return (frameType != VIDEO_FRAME_VIDEO_INFO);
}
