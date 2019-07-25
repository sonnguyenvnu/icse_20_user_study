public EventType evolve(final EventType original,final EventTypeBase eventType) throws InvalidEventTypeException {
  checkEvolutionIncompatibilities(original,eventType);
  final List<SchemaChange> changes=schemaDiff.collectChanges(schema(original),schema(eventType));
  final Version.Level changeLevel=semanticOfChange(original.getSchema().getSchema(),eventType.getSchema().getSchema(),changes,original.getCompatibilityMode());
  if (isForwardToCompatibleUpgrade(original,eventType)) {
    validateCompatibilityModeMigration(changes);
  }
 else   if (original.getCompatibilityMode() != CompatibilityMode.NONE) {
    validateCompatibleChanges(original,changes,changeLevel);
  }
  return bumpVersion(original,eventType,changeLevel);
}
