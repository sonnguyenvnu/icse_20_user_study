protected static byte[][] getParamsWithBinary(List<byte[]> keys,List<byte[]> args){
  final int keyCount=keys.size();
  final int argCount=args.size();
  byte[][] params=new byte[keyCount + argCount][];
  for (int i=0; i < keyCount; i++)   params[i]=keys.get(i);
  for (int i=0; i < argCount; i++)   params[keyCount + i]=args.get(i);
  return params;
}
