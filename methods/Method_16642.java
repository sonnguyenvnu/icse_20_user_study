@Bean public UserInSqlTerm userNotInDistParentSqlTerm(DistrictService districtService){
  return new UserInDistSqlTerm(true,true,"user-not-in-dist-parent",districtService).forParent();
}
