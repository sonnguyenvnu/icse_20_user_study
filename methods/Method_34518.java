public static Object[] createArgsForFallback(Object[] args,MetaHolder metaHolder,Throwable exception){
  if (metaHolder.isExtendedFallback()) {
    if (metaHolder.isExtendedParentFallback()) {
      args[args.length - 1]=exception;
    }
 else {
      args=Arrays.copyOf(args,args.length + 1);
      args[args.length - 1]=exception;
    }
  }
 else {
    if (metaHolder.isExtendedParentFallback()) {
      args=ArrayUtils.remove(args,args.length - 1);
    }
  }
  return args;
}
