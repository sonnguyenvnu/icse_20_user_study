@Override public List<CopyByReference> build(){
  List<String> resolved=new ArrayList<>();
  if (copyByReference != null) {
    for (    String current : copyByReference) {
      resolved.add(elEngine.resolve(current));
    }
  }
  setCopyByReference(resolved);
  return super.build();
}
