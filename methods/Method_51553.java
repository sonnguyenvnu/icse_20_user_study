/** 
 * Returns an Iterator of RuleSet objects loaded from descriptions from the "categories.properties" resource for each Language with Rule support.
 * @return An Iterator of RuleSet objects.
 * @throws RuleSetNotFoundException if the ruleset file could not be found
 */
public Iterator<RuleSet> getRegisteredRuleSets() throws RuleSetNotFoundException {
  String rulesetsProperties=null;
  try {
    List<RuleSetReferenceId> ruleSetReferenceIds=new ArrayList<>();
    for (    Language language : LanguageRegistry.findWithRuleSupport()) {
      Properties props=new Properties();
      rulesetsProperties="category/" + language.getTerseName() + "/categories.properties";
      try (InputStream inputStream=resourceLoader.loadClassPathResourceAsStreamOrThrow(rulesetsProperties)){
        props.load(inputStream);
      }
       String rulesetFilenames=props.getProperty("rulesets.filenames");
      ruleSetReferenceIds.addAll(RuleSetReferenceId.parse(rulesetFilenames));
    }
    return createRuleSets(ruleSetReferenceIds).getRuleSetsIterator();
  }
 catch (  IOException ioe) {
    throw new RuntimeException("Couldn't find " + rulesetsProperties + "; please ensure that the directory is on the classpath. The current classpath is: " + System.getProperty("java.class.path"));
  }
}
