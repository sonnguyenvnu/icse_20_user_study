@Override public void visitProvide(final String service,final String... providers){
  provides.putShort(symbolTable.addConstantClass(service).index);
  provides.putShort(providers.length);
  for (  String provider : providers) {
    provides.putShort(symbolTable.addConstantClass(provider).index);
  }
  providesCount++;
}
