/** 
 * Adds one or more regexes to the set of regexes that define which types should be excluded during the component finding process.
 * @param regexes   one or more regular expressions, as Strings
 */
public void exclude(String... regexes){
  if (regexes != null) {
    for (    String regex : regexes) {
      this.exclusions.add(Pattern.compile(regex));
    }
  }
}
