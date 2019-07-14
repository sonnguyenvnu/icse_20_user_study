/** 
 * Allows &quot;[&quot; or &quot;]&quot; to appear in atext. The address: <ul><li><code>[Kayaks] &lt;kayaks@kayaks.org&gt;</code></li></ul> <p> ...is not valid. It should be: <p> <ul><li><code>&quot;[Kayaks]&quot; &lt;kayaks@kayaks.org&gt;</code></li></ul> <p> If this boolean is set to false, the parser will act per 2822 and will require the quotes; if set to true, it will allow them to be missing. <p> Use at your own risk. There may be some issue with enabling this feature in conjunction with  {@link #allowDomainLiterals(boolean)}.
 */
public RFC2822AddressParser allowSquareBracketsInAtext(final boolean allow){
  ALLOW_SQUARE_BRACKETS_IN_ATEXT=allow;
  resetPatterns();
  return this;
}
