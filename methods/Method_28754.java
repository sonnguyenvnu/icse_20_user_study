protected static String[] getParams(List<String> keys,List<String> args){
  int keyCount=keys.size();
  int argCount=args.size();
  String[] params=new String[keyCount + args.size()];
  for (int i=0; i < keyCount; i++)   params[i]=keys.get(i);
  for (int i=0; i < argCount; i++)   params[keyCount + i]=args.get(i);
  return params;
}
