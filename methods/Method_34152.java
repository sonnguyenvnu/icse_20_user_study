/** 
 * @param configurers the configurers to set
 */
@Autowired(required=false) public void setConfigurers(List<ResourceServerConfigurer> configurers){
  this.configurers=configurers;
}
