/** 
 * Private init method that is called only by constructors. All allocations are done in this method.
 * @param bufSize size of each buffer
 * @param depth maximum depth of the tree of buffers
 */
@VisibleForTesting void init(int bufSize,int depth){
  bufferSize=bufSize;
  maxDepth=depth;
  bufferPool=new long[2][bufferSize];
  indices=new int[depth + 1];
  buffer=new long[depth + 1][bufferSize];
  allocate(0);
  allocate(1);
  Arrays.fill(buffer,2,buffer.length,null);
  clear();
}
