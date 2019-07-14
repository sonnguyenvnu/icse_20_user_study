/** 
 * Parses pts_time from splice_time(), defined in Section 9.4.1. Returns  {@link C#TIME_UNSET}, if time_specified_flag is false.
 * @param sectionData The section data from which the pts_time is parsed.
 * @param ptsAdjustment The pts adjustment provided by the splice info section header.
 * @return The pts_time defined by splice_time(), or {@link C#TIME_UNSET}, if time_specified_flag is false.
 */
static long parseSpliceTime(ParsableByteArray sectionData,long ptsAdjustment){
  long firstByte=sectionData.readUnsignedByte();
  long ptsTime=C.TIME_UNSET;
  if ((firstByte & 0x80) != 0) {
    ptsTime=(firstByte & 0x01) << 32 | sectionData.readUnsignedInt();
    ptsTime+=ptsAdjustment;
    ptsTime&=0x1FFFFFFFFL;
  }
  return ptsTime;
}
