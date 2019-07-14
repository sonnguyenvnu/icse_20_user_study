private void checkOptionsWithDiscrepancies(ModifiableConfiguration globalWrite,BasicConfiguration localBasicConfiguration,ModifiableConfiguration overwrite){
  final boolean managedOverridesAllowed=isManagedOverwritesAllowed(globalWrite,localBasicConfiguration);
  Set<String> optionsWithDiscrepancies=getOptionsWithDiscrepancies(globalWrite,localBasicConfiguration,overwrite,managedOverridesAllowed);
  if (optionsWithDiscrepancies.size() > 0 && !managedOverridesAllowed) {
    final String template="Local settings present for one or more globally managed options: [%s].  These options are controlled through the %s interface; local settings have no effect.";
    throw new JanusGraphConfigurationException(String.format(template,Joiner.on(", ").join(optionsWithDiscrepancies),ManagementSystem.class.getSimpleName()));
  }
}
