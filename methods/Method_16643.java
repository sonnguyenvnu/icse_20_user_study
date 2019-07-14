@Bean public UserInSqlTerm personInDistSqlTerm(DistrictService districtService){
  return new UserInDistSqlTerm(false,false,"person-in-dist",districtService).forPerson();
}
