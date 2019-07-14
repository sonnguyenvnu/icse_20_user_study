public static String randomKey(){
  if (Objects.isNull(theIdGen)) {
    theIdGen=new DefaultIdGen(12,0);
  }
  return theIdGen.nextId();
}
