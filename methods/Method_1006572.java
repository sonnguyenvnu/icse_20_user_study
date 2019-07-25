@Override public Integer call() throws Exception {
  System.out.printf("Finding files of type %s, startingPoint: %s, invoking command '%s' on each...%n",types,startingPoint,execCommandAndArgs);
  System.out.printf("Unmatched positional arguments following ';' were: %s%n",unmatched);
  return 0;
}
