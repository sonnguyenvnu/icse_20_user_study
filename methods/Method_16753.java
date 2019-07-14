public static <U,T extends Query<?,U>>void applyQueryParam(T query,QueryParamEntity entity,BiConsumer<Term,T> notFound){
  Class type=query.getClass();
  for (  Term term : entity.getTerms()) {
    String name=term.getColumn();
    if (TermType.like.equals(term.getTermType())) {
      name=name.concat("Like");
    }
 else     if (TermType.in.equals(term.getTermType())) {
      name=name.concat("s");
    }
    String finalName=name;
    AtomicBoolean found=new AtomicBoolean(false);
    ReflectionUtils.doWithMethods(type,method -> {
      if (method.getParameterCount() == 1 && (method.getName().equals(finalName) || method.getName().equals(term.getColumn()))) {
        Object value=FastBeanCopier.DEFAULT_CONVERT.convert(term.getValue(),method.getParameterTypes()[0],FastBeanCopier.EMPTY_CLASS_ARRAY);
        ReflectionUtils.invokeMethod(method,query,value);
        found.set(true);
      }
    }
);
    if (!found.get()) {
      notFound.accept(term,query);
    }
  }
  for (  Sort sort : entity.getSorts()) {
    String name=sort.getName();
    Method method=ReflectionUtils.findMethod(type,"orderBy" + StringUtils.toUpperCaseFirstOne(name));
    if (method != null && method.getParameterCount() == 0) {
      ReflectionUtils.invokeMethod(method,query);
      if ("asc".equals(sort.getOrder())) {
        query.asc();
      }
 else {
        query.desc();
      }
    }
  }
}
