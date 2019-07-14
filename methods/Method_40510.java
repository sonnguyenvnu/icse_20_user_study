/** 
 * Adds a definition and/or reference to the table. If there is no binding for  {@code id}, creates one and gives it {@code type} and {@code kind}.  <p> <p/> If a binding already exists, then add either a definition or a reference at  {@code loc} to the binding.  By convention we consider it a definitionif the type changes.  If the passed type is different from the binding's current type, set the binding's type to the union of the old and new types, and add a definition.  If the new type is the same, just add a reference.  <p> <p/> If the binding already exists,  {@code kind} is only updated if adefinition was added <em>and</em> the binding's type was previously the unknown type.
 */
public Binding put(String id,Node loc,Type type,Binding.Kind kind,int tag){
  Binding b=lookupScope(id);
  return insertOrUpdate(b,id,loc,type,kind,tag);
}
