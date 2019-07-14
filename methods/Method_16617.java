@Bean public UserInSqlTerm personInPositionChildSqlTerm(PositionService positionService){
  return new UserInPositionSqlTerm(false,true,"person-in-position-child",positionService).forPerson();
}
