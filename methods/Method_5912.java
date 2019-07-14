/** 
 * Returns whether the NAL unit with the specified header contains supplemental enhancement information.
 * @param mimeType The sample MIME type.
 * @param nalUnitHeaderFirstByte The first byte of nal_unit().
 * @return Whether the NAL unit with the specified header is an SEI NAL unit.
 */
public static boolean isNalUnitSei(String mimeType,byte nalUnitHeaderFirstByte){
  return (MimeTypes.VIDEO_H264.equals(mimeType) && (nalUnitHeaderFirstByte & 0x1F) == H264_NAL_UNIT_TYPE_SEI) || (MimeTypes.VIDEO_H265.equals(mimeType) && ((nalUnitHeaderFirstByte & 0x7E) >> 1) == H265_NAL_UNIT_TYPE_PREFIX_SEI);
}
