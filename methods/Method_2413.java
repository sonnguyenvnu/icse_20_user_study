/** 
 * ??????????
 * @param upmsOrganizationExample
 * @return
 */
@Override public List<UpmsOrganization> selectUpmsOrganizationByExample(UpmsOrganizationExample upmsOrganizationExample){
  return upmsOrganizationMapper.selectByExample(upmsOrganizationExample);
}
