/** 
 * Checks if sequence of chars contain an emoji.
 * @param sequence Sequence of char that may contain emoji in full or partially.
 * @return &lt;li&gt; Matches.EXACTLY if char sequence in its entirety is an emoji &lt;/li&gt; &lt;li&gt; Matches.POSSIBLY if char sequencematches prefix of an emoji &lt;/li&gt; &lt;li&gt; Matches.IMPOSSIBLE if char sequence matches no emoji or prefix of an emoji &lt;/li&gt;
 */
public Matches isEmoji(char[] sequence){
  if (sequence == null) {
    return Matches.POSSIBLY;
  }
  Node tree=root;
  for (  char c : sequence) {
    if (!tree.hasChild(c)) {
      return Matches.IMPOSSIBLE;
    }
    tree=tree.getChild(c);
  }
  return tree.isEndOfEmoji() ? Matches.EXACTLY : Matches.POSSIBLY;
}
