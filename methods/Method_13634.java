/** 
 * get all acm properties from application context
 * @return
 */
public List<AcmPropertySource> getAll(){
  List<AcmPropertySource> result=new ArrayList<>();
  ConfigurableApplicationContext ctx=(ConfigurableApplicationContext)applicationContext;
  for (  PropertySource p : ctx.getEnvironment().getPropertySources()) {
    if (p instanceof AcmPropertySource) {
      result.add((AcmPropertySource)p);
    }
 else     if (p instanceof CompositePropertySource) {
      collectAcmPropertySources((CompositePropertySource)p,result);
    }
  }
  return result;
}
