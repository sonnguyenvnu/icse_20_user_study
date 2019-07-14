/** 
 * Split a Java name into terms based on either Camel Case or Underscores. We also split digits at the end of the name into a separate term so as to treat PERSON1 and PERSON_1 as the same thing.
 * @param identifierName to split
 * @return a list of the terms in the name, in order and converted to lowercase
 */
public static ImmutableList<String> splitToLowercaseTerms(String identifierName){
  if (ONLY_UNDERSCORES.matcher(identifierName).matches()) {
    return ImmutableList.of(identifierName);
  }
  return Arrays.stream(TERM_SPLITTER.split(identifierName)).map(String::toLowerCase).collect(toImmutableList());
}
