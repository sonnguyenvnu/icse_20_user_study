static int getAllocationSize(int elements,int elementShift){
  apiCheckAllocation(elements,apiGetBytes(elements,elementShift),0x7FFF_FFFFL);
  return elements << elementShift;
}
