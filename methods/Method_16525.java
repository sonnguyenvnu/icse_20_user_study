@Override protected Term createQueryTerm(Set<String> scope,AuthorizingContext context){
  Term term=new Term();
  term.setColumn(PersonAttachEntity.personId);
  term.setTermType(TermType.in);
  term.setValue(scope);
  term.setType(Term.Type.and);
  return term;
}
