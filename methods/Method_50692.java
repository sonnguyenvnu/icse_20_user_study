void addOperation(String name,ApexOperationSignature sig){
  if (!operations.containsKey(sig)) {
    operations.put(sig,new HashSet<>());
  }
  operations.get(sig).add(name);
}
