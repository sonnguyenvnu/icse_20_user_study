/** 
 * Adds a list of aliases to the item. They will be added to any existing aliases in each language.
 * @param aliases the aliases to add
 */
public ItemUpdateBuilder addAliases(Set<MonolingualTextValue> aliases){
  Validate.isTrue(!built,"ItemUpdate has already been built");
  this.aliases.addAll(aliases);
  return this;
}
