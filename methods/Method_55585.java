/** 
 * Thread-local version of  {@link #ncalloc}. 
 */
public static long nstackCalloc(int alignment,int num,int size){
  return stackGet().ncalloc(alignment,num,size);
}
