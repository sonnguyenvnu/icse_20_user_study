@Bean public UserInSqlTerm userInDistParentSqlTerm(DistrictService districtService){
  return new UserInDistSqlTerm(false,true,"user-in-dist-parent",districtService).forParent();
}
