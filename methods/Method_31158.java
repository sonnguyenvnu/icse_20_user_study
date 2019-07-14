/** 
 * Sets the datasource to use. Must have the necessary privileges to execute ddl.
 * @param dataSource The datasource to use. Must have the necessary privileges to execute ddl.
 */
public void setDataSource(DataSource dataSource){
  driver=null;
  url=null;
  user=null;
  password=null;
  this.dataSource=dataSource;
}
