/** 
 * Resolves loading rules.
 */
protected Loading resolveLoading(final boolean parentFirstStrategy,final String className){
  boolean withParent=true;
  boolean withLoader=true;
  if (parentFirstStrategy) {
    if (isMatchingRules(className,loaderOnlyRules)) {
      withParent=false;
    }
 else     if (isMatchingRules(className,parentOnlyRules)) {
      withLoader=false;
    }
  }
 else {
    if (isMatchingRules(className,parentOnlyRules)) {
      withLoader=false;
    }
 else     if (isMatchingRules(className,loaderOnlyRules)) {
      withParent=false;
    }
  }
  return new Loading(withParent,withLoader);
}
