/** 
 * Sees if buf is of the form [optional anything][keyword][optional whitespace]. It won't allow keyword to be directly preceded by an alphanumeric, _, or &amp;. Will be different if keyword contains regex codes.
 */
private boolean bufEnds(final String keyword){
  return Pattern.matches("^.*(?<![a-zA-Z0-9_&])" + keyword + "\\s*$",buf);
}
