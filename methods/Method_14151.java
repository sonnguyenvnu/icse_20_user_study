/** 
 * Encode special vowel-encoding cases where 'E' is not silent at the end of a word as is the usual case
 * @return true if encoding handled in this routine, false if not
 */
boolean Encode_Vowel_Preserve_Vowel_After_L(int save_current){
  if (m_encodeVowels && !IsVowel(save_current - 1) && (CharAt(save_current + 1) == 'E') && (save_current > 1) && ((save_current + 1) != m_last) && !(StringAt((save_current + 1),2,"ES","ED","") && ((save_current + 2) == m_last)) && !StringAt((save_current - 1),5,"RLEST","")) {
    MetaphAdd("LA");
    m_current=SkipVowels(m_current);
    return true;
  }
  return false;
}
