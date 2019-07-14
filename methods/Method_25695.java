double totalOriginalCost(){
  return originalCost().stream().mapToDouble(d -> d).sum();
}
