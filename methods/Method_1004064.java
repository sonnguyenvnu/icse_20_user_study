@Override public void visit(final int version,final int access,final String name,final String signature,final String superName,final String[] interfaces){
  coverage.setSignature(stringPool.get(signature));
  coverage.setSuperName(stringPool.get(superName));
  coverage.setInterfaces(stringPool.get(interfaces));
}
