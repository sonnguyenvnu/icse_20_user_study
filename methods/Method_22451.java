static public StringDict loadProperties(File contribFolder,ContributionType type){
  File propertiesFile=new File(contribFolder,type.getPropertiesName());
  if (propertiesFile.exists()) {
    return Util.readSettings(propertiesFile);
  }
  return null;
}
