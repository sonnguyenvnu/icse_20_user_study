@Bean public UserInSqlTerm personInPositionSqlTerm(PositionService positionService){
  return new UserInPositionSqlTerm(false,false,"person-in-position",positionService).forPerson();
}
