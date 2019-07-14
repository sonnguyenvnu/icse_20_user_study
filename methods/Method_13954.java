protected Set<PropertyIdValue> mandatoryQualifiers(PropertyIdValue statementProperty){
  Set<PropertyIdValue> mandatory=null;
  if (_mandatoryQualifiers.containsKey(statementProperty)) {
    mandatory=_mandatoryQualifiers.get(statementProperty);
  }
 else {
    mandatory=_fetcher.mandatoryQualifiers(statementProperty);
    if (mandatory == null) {
      mandatory=new HashSet<>();
    }
    _mandatoryQualifiers.put(statementProperty,mandatory);
  }
  return mandatory;
}
