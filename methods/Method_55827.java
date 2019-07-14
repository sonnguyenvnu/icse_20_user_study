@NativeType("CUresult") public static int cuGraphicsMapResources(@NativeType("CUgraphicsResource *") PointerBuffer resources,@NativeType("CUstream") long hStream){
  return ncuGraphicsMapResources(resources.remaining(),memAddress(resources),hStream);
}
