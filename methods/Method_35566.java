@Override public void editStubMapping(StubMapping stubMapping){
  postJsonAssertOkAndReturnBody(urlFor(OldEditStubMappingTask.class),Json.write(stubMapping));
}
