/** 
 * TRUE if id is ambiguous.                              
 */
private boolean Ambiguous(String id){
  int i=0;
  boolean found=false;
  while (i < symtab.size()) {
    SymTabEntry se=(SymTabEntry)symtab.elementAt(i);
    if (se.useThis.equals(id)) {
      if (!found)       found=true;
 else       return true;
    }
    i=i + 1;
  }
  return false;
}
