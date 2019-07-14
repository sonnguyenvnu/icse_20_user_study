/** 
 * Split this encoded string around matches of the given pattern.
 * @param pattern the delimiting pattern
 * @return the array of encoded strings computed by splitting this encodedstring around matches of the given pattern
 */
public EncodedStringValue[] split(String pattern){
  String[] temp=getString().split(pattern);
  EncodedStringValue[] ret=new EncodedStringValue[temp.length];
  for (int i=0; i < ret.length; ++i) {
    try {
      ret[i]=new EncodedStringValue(mCharacterSet,temp[i].getBytes());
    }
 catch (    NullPointerException e) {
      return null;
    }
  }
  return ret;
}
