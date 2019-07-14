/** 
 * Returns true if the value arg is either null, empty, or full of whitespace characters. More efficient that calling (string).trim().length() == 0.
 * @param value String to test
 * @return <code>true</code> if the value is empty, <code>false</code> otherwise.
 * @deprecated {@link StringUtils#isBlank(CharSequence)}
 */
@Deprecated public static boolean isEmpty(String value){
  return StringUtils.isBlank(value);
}
