@Override public void seek(long position,long timeUs){
  state=STATE_READING_FLV_HEADER;
  mediaTagTimestampOffsetUs=C.TIME_UNSET;
  bytesToNextTagHeader=0;
}
