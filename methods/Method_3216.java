protected int argPos(String param,String[] args,boolean checkArgNum){
  for (int i=0; i < args.length; i++) {
    if (param.equals(args[i])) {
      if (checkArgNum && (i == args.length - 1))       throw new IllegalArgumentException(String.format("Argument missing for %s",param));
      return i;
    }
  }
  return -1;
}
