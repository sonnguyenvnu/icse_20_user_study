/** 
 * Tries to load the given ruleset.
 * @param name the ruleset name
 * @return <code>true</code> if the ruleset could be loaded,<code>false</code> otherwise.
 */
private boolean checkRulesetExists(final String name){
  boolean resourceFound=false;
  if (name != null) {
    try (InputStream resource=new ResourceLoader().loadClassPathResourceAsStreamOrThrow(name)){
      resourceFound=true;
    }
 catch (    Exception ignored) {
    }
  }
  return resourceFound;
}
