protected void writeLength(WriteBuffer buffer,Object array){
  if (array == null)   VariableLong.writePositive(buffer,0);
 else {
    long length=((long)Array.getLength(array)) + 1;
    assert length > 0;
    VariableLong.writePositive(buffer,length);
  }
}
