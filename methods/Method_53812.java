/** 
 * Initializes this struct with the specified values. 
 */
public AIExportDataBlob set(ByteBuffer data,AIString name,@Nullable AIExportDataBlob next){
  data(data);
  name(name);
  next(next);
  return this;
}
