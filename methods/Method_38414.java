/** 
 * Creates Decora manager. Override to provide custom decora manager. Alternatively, set it in filter init parameters.
 */
protected DecoraManager createDecoraManager(){
  return new DecoraManager();
}
