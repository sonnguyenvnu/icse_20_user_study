/** 
 * Sets length allocated for output keys. If incoming number is greater than maximum allowable  length returned by GetMaximumKeyLength(), set key length to maximum key length and return false;  otherwise, set key  length to parameter value and return true.
 * @param inKeyLength new length of key.
 * @return true if able to set key length to requested value.
 */
boolean SetKeyLength(int inKeyLength){
  if (inKeyLength < 1) {
    inKeyLength=1;
  }
  if (inKeyLength > MAX_KEY_ALLOCATION) {
    m_metaphLength=MAX_KEY_ALLOCATION;
    return false;
  }
  m_metaphLength=inKeyLength;
  return true;
}
