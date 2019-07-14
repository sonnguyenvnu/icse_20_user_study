/** 
 * Capture values and perform all the case-conversions once per run
 */
@Override public void start(RuleContext ctx){
  originalBadWords=getProperty(DISSALLOWED_TERMS_DESCRIPTOR);
  caseSensitive=getProperty(CASE_SENSITIVE_DESCRIPTOR);
  if (caseSensitive) {
    currentBadWords=originalBadWords;
  }
 else {
    currentBadWords=new ArrayList<>();
    for (    String badWord : originalBadWords) {
      currentBadWords.add(badWord.toUpperCase(Locale.ROOT));
    }
  }
}
