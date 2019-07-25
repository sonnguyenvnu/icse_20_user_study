public static CriteriaBuilder build(Class<?> clz,Paged paged){
  Criteria criteria=new Criteria();
  criteria.setClz(clz);
  CriteriaBuilder builder=new CriteriaBuilder(criteria);
  if (criteria.getParsed() == null) {
    Parsed parsed=Parser.get(clz);
    criteria.setParsed(parsed);
  }
  if (paged != null) {
    builder.paged(paged);
  }
  return builder;
}
