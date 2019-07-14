@Override public ArrayType inline(Inliner inliner) throws CouldNotResolveImportException {
  return new ArrayType(componentType().inline(inliner),inliner.symtab().arrayClass);
}
