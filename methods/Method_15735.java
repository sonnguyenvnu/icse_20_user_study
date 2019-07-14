protected Term createQueryTerm(FieldScopeDataAccessConfig access){
  Term term=new Term();
  term.setType(Term.Type.and);
  term.setColumn(access.getField());
  term.setTermType(TermType.in);
  term.setValue(access.getScope());
  return term;
}
