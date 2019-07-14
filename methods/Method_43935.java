/** 
 * Set the override file for generating the  {@link org.knowm.xchange.dto.meta.ExchangeMetaData}object. By default, the  {@link org.knowm.xchange.dto.meta.ExchangeMetaData} object is loaded atstartup from a json file on the classpath with the same name as the name of the exchange as defined in  {@link ExchangeSpecification}. With this parameter, you can override that file with a file of your choice located outside of the classpath.
 * @return
 */
public void setMetaDataJsonFileOverride(String metaDataJsonFileOverride){
  this.metaDataJsonFileOverride=metaDataJsonFileOverride;
}
