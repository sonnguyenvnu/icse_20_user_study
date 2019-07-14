@Override @Cacheable(key="'by-role-id:'+#roleId") public List<PersonEntity> selectByRoleId(String roleId){
  if (StringUtils.isEmpty(roleId)) {
    return new ArrayList<>();
  }
  return personDao.selectByRoleId(roleId);
}
