/** 
 * The value of a literal token, recorded as a string. For integers, leading 0x and 'l' suffixes are suppressed.
 */
public final String stringVal(){
  if (!hasSpecial) {
    int offset=np + 1;
    if (offset < 0) {
      throw new IllegalStateException();
    }
    if (offset > buf.length - sp) {
      throw new IllegalStateException();
    }
    return new String(buf,offset,sp);
  }
 else {
    return new String(sbuf,0,sp);
  }
}
