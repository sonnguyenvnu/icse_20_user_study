/** 
 * Encode a couple of contexts where scandinavian, slavic or german names should get an alternate, native  pronunciation of 'SV' or 'XV'
 * @return true if handled
 */
boolean Encode_Special_SW(){
  if (m_current == 0) {
    if (Names_Beginning_With_SW_That_Get_Alt_SV()) {
      MetaphAdd("S","SV");
      m_current+=2;
      return true;
    }
    if (Names_Beginning_With_SW_That_Get_Alt_XV()) {
      MetaphAdd("S","XV");
      m_current+=2;
      return true;
    }
  }
  return false;
}
