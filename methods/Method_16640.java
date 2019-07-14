@Bean public UserInSqlTerm personNotInOrgParentSqlTerm(OrganizationalService organizationalService){
  return new UserInOrgSqlTerm(true,true,"person-not-in-org-parent",organizationalService).forPerson().forParent();
}
