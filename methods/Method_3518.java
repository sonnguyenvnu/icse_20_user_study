/** 
 * ???????
 * @param enable
 * @return
 */
public NShortSegment enableOrganizationRecognize(boolean enable){
  config.organizationRecognize=enable;
  config.updateNerConfig();
  return this;
}
