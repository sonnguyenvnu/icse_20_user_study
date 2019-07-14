/** 
 * Initializes this struct with the specified values. 
 */
public CUDA_HOST_NODE_PARAMS set(CUhostFnI fn,long userData){
  fn(fn);
  userData(userData);
  return this;
}
