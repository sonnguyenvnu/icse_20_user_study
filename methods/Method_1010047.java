/** 
 * merges a descriptor into the vTable mainly a method from 'another' descriptor is merged into <code>myIdToImplementationTable</code> if only there is no any record in it yet
 */
public void merge(@NotNull final BHDescriptor descriptor){
  for (  SMethod method : descriptor.getDeclaredMethods()) {
    if (method.isVirtual()) {
      SMethodId id=method.getId();
      if (!myIdToImplementationTable.containsKey(id) || myIdToImplementationTable.get(id).isAbstract()) {
        myIdToImplementationTable.put(id,method);
      }
    }
  }
}
