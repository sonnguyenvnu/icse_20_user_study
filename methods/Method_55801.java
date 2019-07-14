public static int ncuStreamCreateWithPriority(long phStream,int flags,int priority){
  long __functionAddress=Functions.StreamCreateWithPriority;
  return callPI(phStream,flags,priority,__functionAddress);
}
