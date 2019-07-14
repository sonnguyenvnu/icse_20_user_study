/** 
 * Look up a name in the current symbol table only. Don't recurse on the parent table.
 */
@Nullable public Set<Binding> lookupLocal(String name){
  return table.get(name);
}
