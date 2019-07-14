/** 
 * Look up a name in the current symbol table only. Don't recurse on the parent table.
 */
public Binding lookupLocal(String name){
  if (table == null) {
    return null;
  }
 else {
    return table.get(name);
  }
}
