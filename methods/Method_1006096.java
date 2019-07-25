public void save(){
  if (OS.WINDOWS || OS.OS_X) {
    ooPreferences.updateConnectionParams(ooPath.getValue(),ooPath.getValue(),ooPath.getValue());
  }
 else {
    ooPreferences.updateConnectionParams(ooPath.getValue(),ooExec.getValue(),ooJars.getValue());
  }
  preferencesService.setOpenOfficePreferences(ooPreferences);
}
