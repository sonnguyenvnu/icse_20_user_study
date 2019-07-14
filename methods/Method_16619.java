@Bean public UserInSqlTerm personInPositionParentSqlTerm(PositionService positionService){
  return new UserInPositionSqlTerm(false,true,"person-in-position-parent",positionService).forPerson().forParent();
}
