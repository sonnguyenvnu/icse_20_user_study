private static String errorMessage(Name name,TypeParameterNamingClassification classification){
  Preconditions.checkArgument(!classification.isValidName());
  if (classification == TypeParameterNamingClassification.NON_CLASS_NAME_WITH_T_SUFFIX) {
    return String.format("Type Parameters should be an UpperCamelCase name followed by the letter 'T'. " + "%s ends in T, but is not a valid UpperCamelCase name",name);
  }
  return String.format("Type Parameter %s must be a single letter with an optional numeric" + " suffix, or an UpperCamelCase name followed by the letter 'T'.",name);
}
