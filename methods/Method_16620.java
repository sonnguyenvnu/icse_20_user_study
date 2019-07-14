@Bean public UserInSqlTerm personNotInPositionParentSqlTerm(PositionService positionService){
  return new UserInPositionSqlTerm(true,true,"person-not-in-position-parent",positionService).forPerson().forParent();
}
