/** 
 * Initializes this struct with the specified values. 
 */
public AIExportFormatDesc set(ByteBuffer id,ByteBuffer description,ByteBuffer fileExtension){
  id(id);
  description(description);
  fileExtension(fileExtension);
  return this;
}
