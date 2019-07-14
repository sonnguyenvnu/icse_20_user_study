/** 
 * Adjusts a  {@link Format} to incorporate a sample offset into {@link Format#subsampleOffsetUs}.
 * @param format The {@link Format} to adjust.
 * @param sampleOffsetUs The offset to apply.
 * @return The adjusted {@link Format}.
 */
private static Format getAdjustedSampleFormat(Format format,long sampleOffsetUs){
  if (format == null) {
    return null;
  }
  if (sampleOffsetUs != 0 && format.subsampleOffsetUs != Format.OFFSET_SAMPLE_RELATIVE) {
    format=format.copyWithSubsampleOffsetUs(format.subsampleOffsetUs + sampleOffsetUs);
  }
  return format;
}
