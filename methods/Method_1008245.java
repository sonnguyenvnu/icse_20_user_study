private void validate(PutRequest request){
  List<String> validationErrors=new ArrayList<>();
  if (request.name.contains(" ")) {
    validationErrors.add("name must not contain a space");
  }
  if (request.name.contains(",")) {
    validationErrors.add("name must not contain a ','");
  }
  if (request.name.contains("#")) {
    validationErrors.add("name must not contain a '#'");
  }
  if (request.name.startsWith("_")) {
    validationErrors.add("name must not start with '_'");
  }
  if (!request.name.toLowerCase(Locale.ROOT).equals(request.name)) {
    validationErrors.add("name must be lower cased");
  }
  for (  String indexPattern : request.indexPatterns) {
    if (indexPattern.contains(" ")) {
      validationErrors.add("template must not contain a space");
    }
    if (indexPattern.contains(",")) {
      validationErrors.add("template must not contain a ','");
    }
    if (indexPattern.contains("#")) {
      validationErrors.add("template must not contain a '#'");
    }
    if (indexPattern.startsWith("_")) {
      validationErrors.add("template must not start with '_'");
    }
    if (!Strings.validFileNameExcludingAstrix(indexPattern)) {
      validationErrors.add("template must not contain the following characters " + Strings.INVALID_FILENAME_CHARS);
    }
  }
  try {
    indexScopedSettings.validate(request.settings,true);
  }
 catch (  IllegalArgumentException iae) {
    validationErrors.add(iae.getMessage());
    for (    Throwable t : iae.getSuppressed()) {
      validationErrors.add(t.getMessage());
    }
  }
  List<String> indexSettingsValidation=metaDataCreateIndexService.getIndexSettingsValidationErrors(request.settings);
  validationErrors.addAll(indexSettingsValidation);
  if (!validationErrors.isEmpty()) {
    ValidationException validationException=new ValidationException();
    validationException.addValidationErrors(validationErrors);
    throw new InvalidIndexTemplateException(request.name,validationException.getMessage());
  }
  for (  Alias alias : request.aliases) {
    aliasValidator.validateAliasStandalone(alias);
    if (request.indexPatterns.contains(alias.name())) {
      throw new IllegalArgumentException("Alias [" + alias.name() + "] cannot be the same as any pattern in [" + String.join(", ",request.indexPatterns) + "]");
    }
  }
}
