/** 
 * Sets the content of the output buffer, consisting of a  {@link Subtitle} and associatedmetadata.
 * @param timeUs The time of the start of the subtitle in microseconds.
 * @param subtitle The subtitle.
 * @param subsampleOffsetUs An offset that must be added to the subtitle's event times, or{@link Format#OFFSET_SAMPLE_RELATIVE} if {@code timeUs} should be added.
 */
public void setContent(long timeUs,Subtitle subtitle,long subsampleOffsetUs){
  this.timeUs=timeUs;
  this.subtitle=subtitle;
  this.subsampleOffsetUs=subsampleOffsetUs == Format.OFFSET_SAMPLE_RELATIVE ? this.timeUs : subsampleOffsetUs;
}
