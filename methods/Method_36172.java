public static StubMapping buildFrom(String mappingSpecJson){
  return Json.read(mappingSpecJson,StubMapping.class);
}
