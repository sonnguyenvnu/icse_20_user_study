/** 
 * Return node image converted to the normal Oracle form. <p> Normally this is uppercase, unless the names is quoted ("name"). </p>
 */
public String getCanonicalImage(){
  return PLSQLParser.canonicalName(this.getImage());
}
