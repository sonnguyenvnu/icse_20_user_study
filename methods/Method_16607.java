@Bean public InServiceTreeInSqlTerm<String> orgInSqlTerm(OrganizationalService organizationalService){
  return new InServiceTreeInSqlTerm<>(organizationalService,"org","s_organization",false,false);
}
