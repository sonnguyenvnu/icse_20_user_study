/** 
 * Applies options to this  {@link ScannerSupplier}. <p>Command-line options to override check severities may do any of the following: <ul> <li>Enable a check that is currently off <li>Disable a check that is currently on <li>Change the severity of a check that is on, promoting a warning to an error or demoting an error to a warning </ul>
 * @param errorProneOptions an {@link ErrorProneOptions} object that encapsulates the overridesfor this compilation
 * @throws InvalidCommandLineOptionException if the override map attempts to disable a check thatmay not be disabled
 */
@CheckReturnValue public ScannerSupplier applyOverrides(ErrorProneOptions errorProneOptions){
  Map<String,Severity> severityOverrides=errorProneOptions.getSeverityMap();
  if (severityOverrides.isEmpty() && errorProneOptions.getFlags().isEmpty() && !errorProneOptions.isEnableAllChecksAsWarnings() && !errorProneOptions.isDropErrorsToWarnings() && !errorProneOptions.isDisableAllChecks()) {
    return this;
  }
  ImmutableBiMap<String,BugCheckerInfo> checks=getAllChecks();
  Map<String,SeverityLevel> severities=new LinkedHashMap<>(severities());
  Set<String> disabled=new HashSet<>(disabled());
  Map<String,String> flagsMap=new HashMap<>(getFlags().getFlagsMap());
  if (errorProneOptions.isEnableAllChecksAsWarnings()) {
    disabled.forEach(c -> severities.put(c,SeverityLevel.WARNING));
    disabled.clear();
  }
  if (errorProneOptions.isDropErrorsToWarnings()) {
    getAllChecks().values().stream().filter(c -> c.defaultSeverity() == SeverityLevel.ERROR && c.disableable()).forEach(c -> severities.put(c.canonicalName(),SeverityLevel.WARNING));
  }
  if (errorProneOptions.isDisableAllChecks()) {
    getAllChecks().values().stream().filter(c -> c.disableable()).forEach(c -> disabled.add(c.canonicalName()));
  }
  severityOverrides.forEach((checkName,newSeverity) -> {
    BugCheckerInfo check=getAllChecks().get(checkName);
    if (check == null) {
      if (errorProneOptions.ignoreUnknownChecks()) {
        return;
      }
      throw new InvalidCommandLineOptionException(checkName + " is not a valid checker name");
    }
switch (newSeverity) {
case OFF:
      if (!check.disableable()) {
        throw new InvalidCommandLineOptionException(check.canonicalName() + " may not be disabled");
      }
    severities.remove(check.canonicalName());
  disabled.add(check.canonicalName());
break;
case DEFAULT:
severities.put(check.canonicalName(),check.defaultSeverity());
disabled.remove(check.canonicalName());
break;
case WARN:
if (!disabled().contains(check.canonicalName()) && !check.disableable() && check.defaultSeverity() == SeverityLevel.ERROR) {
throw new InvalidCommandLineOptionException(check.canonicalName() + " is not disableable and may not be demoted to a warning");
}
severities.put(check.canonicalName(),SeverityLevel.WARNING);
disabled.remove(check.canonicalName());
break;
case ERROR:
severities.put(check.canonicalName(),SeverityLevel.ERROR);
disabled.remove(check.canonicalName());
break;
default :
throw new IllegalStateException("Unexpected severity level: " + newSeverity);
}
}
);
flagsMap.putAll(errorProneOptions.getFlags().getFlagsMap());
return new ScannerSupplierImpl(checks,ImmutableMap.copyOf(severities),ImmutableSet.copyOf(disabled),ErrorProneFlags.fromMap(flagsMap));
}
