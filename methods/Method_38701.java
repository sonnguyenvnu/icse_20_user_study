/** 
 * Sets local class meta-data name.
 */
public JsonSerializer withClassMetadata(final boolean useMetadata){
  if (useMetadata) {
    classMetadataName=Defaults.DEFAULT_CLASS_METADATA_NAME;
  }
 else {
    classMetadataName=null;
  }
  return this;
}
