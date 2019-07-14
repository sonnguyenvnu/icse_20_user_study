/** 
 * Lookup for named parameter.
 */
DbQueryNamedParameter lookupNamedParameter(final String name){
  DbQueryNamedParameter p=rootNP;
  while (p != null) {
    if (p.equalsName(name)) {
      return p;
    }
    p=p.next;
  }
  return null;
}
