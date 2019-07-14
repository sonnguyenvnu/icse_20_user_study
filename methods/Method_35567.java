@Override public void removeStubMapping(StubMapping stubbMapping){
  postJsonAssertOkAndReturnBody(urlFor(OldRemoveStubMappingTask.class),Json.write(stubbMapping));
}
