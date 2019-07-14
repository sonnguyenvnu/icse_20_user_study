/** 
 * Reads an instance from a  {@link DataInputStream}.
 * @param version Version of the encoded data.
 * @param input Input stream containing values needed to initialize CachedContent instance.
 * @throws IOException If an error occurs during reading values.
 */
public static CachedContent readFromStream(int version,DataInputStream input) throws IOException {
  int id=input.readInt();
  String key=input.readUTF();
  CachedContent cachedContent=new CachedContent(id,key);
  if (version < VERSION_METADATA_INTRODUCED) {
    long length=input.readLong();
    ContentMetadataMutations mutations=new ContentMetadataMutations();
    ContentMetadataMutations.setContentLength(mutations,length);
    cachedContent.applyMetadataMutations(mutations);
  }
 else {
    cachedContent.metadata=DefaultContentMetadata.readFromStream(input);
  }
  return cachedContent;
}
