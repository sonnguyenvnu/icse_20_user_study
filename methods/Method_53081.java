/** 
 * Advances past all input up to and including the next occurrence of {@code thru}. If the remaining input doesn't contain  {@code thru}, the input is exhausted.
 * @param thru The string to skip over.
 */
public void skipPast(String thru){
  int thruStart=in.indexOf(thru,pos);
  pos=thruStart == -1 ? in.length() : (thruStart + thru.length());
}
