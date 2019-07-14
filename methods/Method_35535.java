public static SingleStubMappingResult fromOptional(Optional<StubMapping> optional){
  return new SingleStubMappingResult(optional.orNull());
}
