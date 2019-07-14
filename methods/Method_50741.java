private void extractObjectAndFields(final List<Identifier> listIdentifiers,final String method,final String definingType){
  final List<String> strings=listIdentifiers.stream().map(id -> id.getValue()).collect(Collectors.toList());
  int flsIndex=Collections.lastIndexOfSubList(strings,Arrays.asList(RESERVED_KEYS_FLS));
  if (flsIndex != -1) {
    String objectTypeName=strings.get(flsIndex + RESERVED_KEYS_FLS.length);
    if (!typeToDMLOperationMapping.get(definingType + ":" + objectTypeName).contains(method)) {
      typeToDMLOperationMapping.put(definingType + ":" + objectTypeName,method);
    }
  }
}
