/** 
 * set db type.
 * @param dbType dbType
 */
public void setDbType(final String dbType){
  this.dbType=DbTypeUtils.buildByDriverClassName(dbType);
}
