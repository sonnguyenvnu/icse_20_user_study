/** 
 * ???????
 * @param enable
 * @return
 */
public Segment enableOrganizationRecognize(boolean enable){
  config.organizationRecognize=enable;
  config.updateNerConfig();
  return this;
}
