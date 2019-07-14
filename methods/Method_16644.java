@Bean public UserInSqlTerm personNotInDistSqlTerm(DistrictService districtService){
  return new UserInDistSqlTerm(true,false,"person-not-in-dist",districtService).forPerson();
}
