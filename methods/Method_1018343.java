/** 
 * Applies the Patch to a given Object graph. Makes a copy of the given object so that it will remain unchanged after application of the patch and in case any errors occur while performing the patch.
 * @param in The object graph to apply the patch to.
 * @param type The object type.
 * @param < T > the object type.
 * @return An object graph modified by the patch.
 * @throws PatchException if there are any errors while applying the patch.
 */
public <T>T apply(T in,Class<T> type) throws PatchException {
  for (  PatchOperation operation : operations) {
    operation.perform(in,type);
  }
  return in;
}
