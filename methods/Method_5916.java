/** 
 * Parses a PPS NAL unit using the syntax defined in ITU-T Recommendation H.264 (2013) subsection 7.3.2.2.
 * @param nalData A buffer containing escaped PPS data.
 * @param nalOffset The offset of the NAL unit header in {@code nalData}.
 * @param nalLimit The limit of the NAL unit in {@code nalData}.
 * @return A parsed representation of the PPS data.
 */
public static PpsData parsePpsNalUnit(byte[] nalData,int nalOffset,int nalLimit){
  ParsableNalUnitBitArray data=new ParsableNalUnitBitArray(nalData,nalOffset,nalLimit);
  data.skipBits(8);
  int picParameterSetId=data.readUnsignedExpGolombCodedInt();
  int seqParameterSetId=data.readUnsignedExpGolombCodedInt();
  data.skipBit();
  boolean bottomFieldPicOrderInFramePresentFlag=data.readBit();
  return new PpsData(picParameterSetId,seqParameterSetId,bottomFieldPicOrderInFramePresentFlag);
}
