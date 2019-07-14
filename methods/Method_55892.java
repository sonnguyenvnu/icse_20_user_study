public static int ncuDeviceGetP2PAttribute(long value,int attrib,int srcDevice,int dstDevice){
  long __functionAddress=Functions.DeviceGetP2PAttribute;
  return callPI(value,attrib,srcDevice,dstDevice,__functionAddress);
}
