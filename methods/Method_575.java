/** 
 * The value of a literal token, recorded as a string. For integers, leading 0x and 'l' suffixes are suppressed.
 */
public final String stringVal(){
  if (!hasSpecial) {
    return this.subString(np + 1,sp);
  }
 else {
    return new String(sbuf,0,sp);
  }
}
