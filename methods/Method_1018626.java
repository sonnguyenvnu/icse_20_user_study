public static List<ByteArrayWrapper> split(byte[] data,int size){
  if (data.length % size != 0)   throw new IllegalStateException("Can only split an array that is multiple of split size! " + data.length + " !/ " + size);
  List<ByteArrayWrapper> res=new ArrayList<>(data.length / size);
  for (int i=0; i < data.length / size; i++)   res.add(new ByteArrayWrapper(Arrays.copyOfRange(data,i * size,(i + 1) * size)));
  return res;
}
