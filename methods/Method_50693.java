public boolean hasMatchingSig(String operation,ApexOperationSigMask mask){
  for (  Entry<ApexOperationSignature,Set<String>> entry : operations.entrySet()) {
    if (mask.covers(entry.getKey())) {
      if (entry.getValue().contains(operation)) {
        return true;
      }
    }
  }
  return false;
}
