/** 
 * Encode "-R-"
 */
void Encode_R(){
  if (Encode_RZ()) {
    return;
  }
  if (!Test_Silent_R()) {
    if (!Encode_Vowel_RE_Transposition()) {
      MetaphAdd("R");
    }
  }
  if ((CharAt(m_current + 1) == 'R') || StringAt((m_current - 6),8,"POITIERS","")) {
    m_current+=2;
  }
 else {
    m_current++;
  }
}
