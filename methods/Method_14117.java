/** 
 * Detect names or words that begin with spellings typical of german or slavic words, for the purpose of choosing alternate pronunciations correctly
 */
boolean SlavoGermanic(){
  if (StringAt(0,3,"SCH","") || StringAt(0,2,"SW","") || (CharAt(0) == 'J') || (CharAt(0) == 'W')) {
    return true;
  }
  return false;
}
