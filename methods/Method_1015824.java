/** 
 * Appends the components to the builder and makes the last element the current target for formatting. You can specify the amount of formatting retained from previous part.
 * @param components the components to append
 * @param retention the formatting to retain
 * @return this ComponentBuilder for chaining
 */
public ComponentBuilder append(BaseComponent[] components,FormatRetention retention){
  Preconditions.checkArgument(components.length != 0,"No components to append");
  BaseComponent previous=current;
  for (  BaseComponent component : components) {
    parts.add(current);
    current=component.duplicate();
    current.copyFormatting(previous,retention,false);
  }
  return this;
}
