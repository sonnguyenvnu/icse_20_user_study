/** 
 * Return a content-based String representation if  {@code obj} isnot  {@code null}; otherwise returns an empty String. <p>Differs from  {@link #nullSafeToString(Object)} in that it returnsan empty String rather than "null" for a  {@code null} value.
 * @param obj the object to build a display String for
 * @return a display String representation of {@code obj}
 * @see #nullSafeToString(Object)
 */
public static String getDisplayString(Object obj){
  if (obj == null) {
    return EMPTY_STRING;
  }
  return nullSafeToString(obj);
}
