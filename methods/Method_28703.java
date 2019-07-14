public void sentinel(final String... args){
  final byte[][] arg=new byte[args.length][];
  for (int i=0; i < arg.length; i++) {
    arg[i]=SafeEncoder.encode(args[i]);
  }
  sentinel(arg);
}
