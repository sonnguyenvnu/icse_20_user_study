private byte[] serialize(){
  byte[] data=new byte[bigBlockSize.getBigBlockSize()];
  int offset=0;
  for (int i=0; i < _values.length; i++) {
    LittleEndian.putInt(data,offset,_values[i]);
    offset+=LittleEndian.INT_SIZE;
  }
  return data;
}
