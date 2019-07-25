@Override public String normalize(String tapeName){
  return FilenameNormalizer.toFilename(tapeName) + ".yaml";
}
