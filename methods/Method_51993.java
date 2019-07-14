/** 
 * Adds an operation to the class.
 * @param name The name of the operation
 * @param sig  The signature of the operation
 */
void addOperation(String name,JavaOperationSignature sig){
  if (!operations.containsKey(sig)) {
    operations.put(sig,new HashSet<String>());
  }
  operations.get(sig).add(name);
}
