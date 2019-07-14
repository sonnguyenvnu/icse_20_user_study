static private File getFileForContrib(ModeContribution contrib,Map<File,ModeContribution> known){
  for (  Entry<File,ModeContribution> entry : known.entrySet()) {
    if (entry.getValue() == contrib) {
      return entry.getKey();
    }
  }
  return null;
}
