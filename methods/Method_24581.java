/** 
 * Sees if buf is of the form [optional whitespace][keyword][optional anything]. It won't allow keyword to be directly followed by an alphanumeric, _, or &amp;. Will be different if keyword contains regex codes.
 */
private boolean bufStarts(final String keyword){
  return Pattern.matches("^\\s*" + keyword + "(?![a-zA-Z0-9_&]).*$",buf);
}
