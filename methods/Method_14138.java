/** 
 * Exceptions to default encoding to 'J': encode "-G-" to 'G' in "-g<frontvowel>-" words where we are not at "-GE" at the end of the word
 * @return true if encoding handled in this routine, false if not
 */
boolean Internal_Hard_G(){
  if (!(((m_current + 1) == m_last) && (CharAt(m_current + 1) == 'E')) && (Internal_Hard_NG() || Internal_Hard_GEN_GIN_GET_GIT() || Internal_Hard_G_Open_Syllable() || Internal_Hard_G_Other())) {
    return true;
  }
  return false;
}
