/** 
 * Allows &quot;.&quot; to appear in atext (note: only atext which appears in the 2822 &quot;name-addr&quot; part of the address, not the other instances). <p> The addresses: <ul> <li><code>Kayaks.org &lt;kayaks@kayaks.org&gt;</code></li> <li><code>Bob K. Smith&lt;bobksmith@bob.net&gt;</code></li> </ul> ...are not valid. They should be: <ul> <li><code>&quot;Kayaks.org&quot; &lt;kayaks@kayaks.org&gt;</code></li> <li><code>&quot;Bob K. Smith&quot; &lt;bobksmith@bob.net&gt;</code></li> </ul> If this boolean is set to false, the parser will act per 2822 and will require the quotes; if set to true, it will allow the use of &quot;.&quot; without quotes.
 */
public RFC2822AddressParser allowDotInAtext(final boolean allow){
  ALLOW_DOT_IN_ATEXT=allow;
  resetPatterns();
  return this;
}
