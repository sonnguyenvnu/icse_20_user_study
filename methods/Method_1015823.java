/** 
 * Appends a component to the builder and makes it the current target for formatting. You can specify the amount of formatting retained from previous part.
 * @param component the component to append
 * @param retention the formatting to retain
 * @return this ComponentBuilder for chaining
 */
public ComponentBuilder append(BaseComponent component,FormatRetention retention){
  parts.add(current);
  BaseComponent previous=current;
  current=component.duplicate();
  current.copyFormatting(previous,retention,false);
  return this;
}
