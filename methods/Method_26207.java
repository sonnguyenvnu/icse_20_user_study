private String getSuggestedSignature(){
  return String.format("%s(%s)",methodTree().getName(),getSuggestedParameters());
}
