public static int ncuGraphicsUnmapResources(int count,long resources,long hStream){
  long __functionAddress=Functions.GraphicsUnmapResources;
  return callPPI(count,resources,hStream,__functionAddress);
}
