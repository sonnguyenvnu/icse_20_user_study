@Bean public UserInSqlTerm personNotInDistParentSqlTerm(DistrictService districtService){
  return new UserInDistSqlTerm(true,true,"person-not-in-dist-parent",districtService).forPerson().forParent();
}
