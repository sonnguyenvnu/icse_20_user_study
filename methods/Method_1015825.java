/** 
 * Appends the text to the builder and makes it the current target for formatting. You can specify the amount of formatting retained from previous part.
 * @param text the text to append
 * @param retention the formatting to retain
 * @return this ComponentBuilder for chaining
 */
public ComponentBuilder append(String text,FormatRetention retention){
  parts.add(current);
  BaseComponent old=current;
  current=new TextComponent(text);
  current.copyFormatting(old,retention,false);
  return this;
}
