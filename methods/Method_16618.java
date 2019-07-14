@Bean public UserInSqlTerm personNotInPositionChildSqlTerm(PositionService positionService){
  return new UserInPositionSqlTerm(true,true,"person-not-in-position-child",positionService).forPerson();
}
