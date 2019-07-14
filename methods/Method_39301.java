/** 
 * Allows &quot;)&quot; or &quot;(&quot; to appear in quoted versions of the localpart (they are never allowed in unquoted versions). The default (2822) behavior is to allow this, i.e. boolean true. You can disallow it, but better to leave it true.
 */
public RFC2822AddressParser allowParentheseInLocalpart(final boolean allow){
  ALLOW_PARENS_IN_LOCALPART=allow;
  resetPatterns();
  return this;
}
