/** 
 * Encode 'K'
 */
void Encode_K(){
  if (!Encode_Silent_K()) {
    MetaphAdd("K");
    if ((CharAt(m_current + 1) == 'K') || (CharAt(m_current + 1) == 'Q')) {
      m_current+=2;
    }
 else {
      m_current++;
    }
  }
}
