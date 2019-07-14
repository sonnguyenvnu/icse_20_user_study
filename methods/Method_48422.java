protected int compareTo(int length,StaticArrayBuffer buffer,int bufferLen){
  assert buffer != null;
  Preconditions.checkArgument(length <= length() && bufferLen <= buffer.length());
  return compareTo(array,offset,offset + length,buffer.array,buffer.offset,buffer.offset + bufferLen);
}
