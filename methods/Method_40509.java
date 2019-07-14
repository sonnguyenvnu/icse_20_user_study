/** 
 * Directly assigns a binding to a name in this table.  Does not add a new definition or reference to the binding.  This form of  {@code put} isoften followed by a call to  {@link putLocation} to create a reference tothe binding.  When there is no code location associated with  {@code id}, or it is otherwise undesirable to create a reference, the {@link putLocation} call is omitted.
 */
public void put(String id,Binding b){
  getInternalTable().put(id,b);
}
