public boolean load(ByteArray byteArray,V[] value){
  if (byteArray == null)   return false;
  size=byteArray.nextInt();
  base=new int[size + 65535];
  check=new int[size + 65535];
  for (int i=0; i < size; i++) {
    base[i]=byteArray.nextInt();
    check[i]=byteArray.nextInt();
  }
  v=value;
  return true;
}
