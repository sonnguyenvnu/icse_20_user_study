public boolean isSkipJvmReferenceHealthCheck(){
  return SofaRuntimeProperties.isSkipJvmReferenceHealthCheck(this.getClass().getClassLoader());
}
