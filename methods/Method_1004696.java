public void validate(){
  if (this.version == null && this.mappings.isEmpty()) {
    throw new InvalidInitializrMetadataException("No version available for " + this);
  }
  updateVersionRange(VersionParser.DEFAULT);
}
