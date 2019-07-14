@Bean public UserInSqlTerm personNotInDistChildSqlTerm(DistrictService districtService){
  return new UserInDistSqlTerm(true,true,"person-not-in-dist-child",districtService).forPerson();
}
