/** 
 * <p>Gets an estimate for the maximum string length that the formatter will produce.</p> <p/> <p>The actual formatted length will almost always be less than or equal to this amount.</p>
 * @return the maximum formatted length
 */
public int getMaxLengthEstimate(){
  return printer.getMaxLengthEstimate();
}
