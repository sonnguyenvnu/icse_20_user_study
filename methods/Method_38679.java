/** 
 * Sets usage of default class meta-data name. Using it may introduce a security hole, see  {@link #setClassMetadataName(String)} for more details.
 * @see #setClassMetadataName(String)
 */
public JsonParser withClassMetadata(final boolean useMetadata){
  if (useMetadata) {
    classMetadataName=Defaults.DEFAULT_CLASS_METADATA_NAME;
  }
 else {
    classMetadataName=null;
  }
  return this;
}
