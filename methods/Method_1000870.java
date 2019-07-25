/** 
 * Static accessor for a stateless instance used as marker, to indicate that all input `null` values should be skipped (ignored), so that no corresponding property value is set (with POJOs), and no content values (array/Collection elements, Map entries) are added.
 */
public static NullsConstantProvider skipper(){
  return SKIPPER;
}
