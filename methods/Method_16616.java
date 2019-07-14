@Bean public UserInSqlTerm personNotInPositionSqlTerm(PositionService positionService){
  return new UserInPositionSqlTerm(true,false,"person-not-in-position",positionService).forPerson();
}
