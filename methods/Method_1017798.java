/** 
 * <p>Tells this generator to produce values that have a specified {@linkplain Precision#scale() scale}.</p> <p>With no precision constraint and no  {@linkplain #configure(InRange) min/max constraint}, the scale of the generated values is unspecified.</p> <p>Otherwise, the scale of the generated values is set as {@code max(0, precision.scale, range.min.scale, range.max.scale)}.</p>
 * @param configuration annotation that gives the desired scale of thegenerated values
 */
public void configure(Precision configuration){
  precision=configuration;
}
