/** 
 * Check for disagreement between local and backend values for GLOBAL(_OFFLINE) and FIXED options The point of this check is to find edits to the local config which have no effect (and therefore likely indicate misconfiguration)
 * @return Options with discrepancies
 */
private Set<String> getOptionsWithDiscrepancies(ModifiableConfiguration globalWrite,BasicConfiguration localBasicConfiguration,ModifiableConfiguration overwrite,boolean managedOverridesAllowed){
  Set<String> optionsWithDiscrepancies=Sets.newHashSet();
  for (  Map.Entry<ConfigElement.PathIdentifier,Object> entry : getManagedSubset(localBasicConfiguration.getAll()).entrySet()) {
    ConfigElement.PathIdentifier pathId=entry.getKey();
    assert pathId.element.isOption();
    ConfigOption<?> configOption=(ConfigOption<?>)pathId.element;
    Object localValue=entry.getValue();
    Object storeValue=globalWrite.get(configOption,pathId.umbrellaElements);
    if (overwrite.has(configOption,pathId.umbrellaElements)) {
      storeValue=overwrite.get(configOption,pathId.umbrellaElements);
    }
    final boolean match=Objects.equals(localValue,storeValue);
    if (!match) {
      final String fullOptionName=ConfigElement.getPath(pathId.element,pathId.umbrellaElements);
      final String template="Local setting {}={} (Type: {}) is overridden by globally managed value ({}).  Use the {} interface instead of the local configuration to control this setting.";
      Object[] replacements=new Object[]{fullOptionName,localValue,configOption.getType(),storeValue,ManagementSystem.class.getSimpleName()};
      if (managedOverridesAllowed) {
        log.warn(template,replacements);
      }
 else {
        log.error(template,replacements);
      }
      optionsWithDiscrepancies.add(fullOptionName);
    }
  }
  return optionsWithDiscrepancies;
}
