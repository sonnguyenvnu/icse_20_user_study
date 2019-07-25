/** 
 * <p>Tells this generator to generate the values  {@code true} and{@code false} on alternating requests.</p><p>Without this configuration,  {@code true} and {@code false} aregenerated with approximately equal probability.</p>
 * @param flag annotation to turn off random generation and replace itwith alternating values
 */
public void configure(ValuesOf flag){
  turnOffRandomness=flag;
}
