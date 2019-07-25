public static Optional<CustomEntryType> parse(String comment){
  String rest=comment.substring(ENTRYTYPE_FLAG.length());
  int indexEndOfName=rest.indexOf(':');
  if (indexEndOfName < 0) {
    return Optional.empty();
  }
  String fieldsDescription=rest.substring(indexEndOfName + 2);
  int indexEndOfRequiredFields=fieldsDescription.indexOf(']');
  int indexEndOfOptionalFields=fieldsDescription.indexOf(']',indexEndOfRequiredFields + 1);
  if ((indexEndOfRequiredFields < 4) || (indexEndOfOptionalFields < (indexEndOfRequiredFields + 6))) {
    return Optional.empty();
  }
  String name=rest.substring(0,indexEndOfName);
  String reqFields=fieldsDescription.substring(4,indexEndOfRequiredFields);
  String optFields=fieldsDescription.substring(indexEndOfRequiredFields + 6,indexEndOfOptionalFields);
  return Optional.of(new CustomEntryType(name,reqFields,optFields));
}
