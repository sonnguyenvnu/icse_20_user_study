/** 
 * Assumes that the ruleset name given is e.g. "java-basic". Then it will return the full classpath name for the ruleset, in this example it would return "rulesets/java/basic.xml".
 * @param name the ruleset name
 * @return the full classpath to the ruleset
 */
private String resolveBuiltInRuleset(final String name){
  String result=null;
  if (name != null) {
    int index=name.indexOf('-');
    if (index >= 0) {
      result="rulesets/" + name.substring(0,index) + '/' + name.substring(index + 1) + ".xml";
    }
 else {
      if (name.matches("[0-9]+.*")) {
        result="rulesets/releases/" + name + ".xml";
      }
 else {
        result=name;
      }
    }
  }
  return result;
}
