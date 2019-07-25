/** 
 * Returns an array of the names contained in the metadata.
 * @return Metadata names
 */
public String[] names(){
  return metadata.keySet().toArray(new String[metadata.keySet().size()]);
}
