/** 
 * Same as  {@link #put}, but adds the name as an attribute of this scope. Looks up the superclass chain to see if the attribute exists, rather than looking in the lexical scope chain.
 * @return the new binding, or {@code null} if the current scope doesnot have a properly initialized path.
 */
public Binding putAttr(String id,Node loc,Type type,Binding.Kind kind,int tag){
  if ("".equals(path)) {
    Indexer.idx.reportFailedAssertion("Attempting to set attr '" + id + "' at location " + loc + (loc != null ? loc.getFile() : "") + " in scope with no path (qname) set: " + toShortString());
    return null;
  }
 else {
    Binding b=lookupAttr(id);
    return insertOrUpdate(b,id,loc,type,kind,tag);
  }
}
