/** 
 * Returns a set of all types defined within this source file. This includes all top-level types and nested types.
 * @return set of all types in this source file.
 */
public Map<String,Node> getQualifiedTypeNames(){
  if (qualifiedTypeNames != null) {
    return qualifiedTypeNames;
  }
  qualifiedTypeNames=getSubTypes(null,this);
  return qualifiedTypeNames;
}
