private Collection<? extends CodeTransformer> createMatchers(Iterable<UTypeVar> typeVars,String qualifiedTemplateClass,ImmutableClassToInstanceMap<Annotation> annotationMap){
  if (beforeTemplates.isEmpty() && afterTemplates.isEmpty()) {
    return ImmutableList.of();
  }
 else {
    if (annotationMap.containsKey(AllowCodeBetweenLines.class)) {
      List<UBlank> blanks=new ArrayList<>();
      for (int i=0; i < beforeTemplates.size(); i++) {
        BlockTemplate before=(BlockTemplate)beforeTemplates.get(i);
        List<UStatement> stmtsWithBlanks=new ArrayList<>();
        for (        UStatement stmt : before.templateStatements()) {
          if (!stmtsWithBlanks.isEmpty()) {
            UBlank blank=UBlank.create();
            blanks.add(blank);
            stmtsWithBlanks.add(blank);
          }
          stmtsWithBlanks.add(stmt);
        }
        beforeTemplates.set(i,before.withStatements(stmtsWithBlanks));
      }
      for (int i=0; i < afterTemplates.size(); i++) {
        BlockTemplate afterBlock=(BlockTemplate)afterTemplates.get(i);
        afterTemplates.set(i,afterBlock.withStatements(Iterables.concat(blanks,afterBlock.templateStatements())));
      }
    }
    RefasterRule<?,?> rule=RefasterRule.create(qualifiedTemplateClass,typeVars,beforeTemplates,afterTemplates,annotationMap);
    List<ExpressionTemplate> negatedAfterTemplates=new ArrayList<>();
    for (    Template<?> afterTemplate : afterTemplates) {
      if (afterTemplate.annotations().containsKey(AlsoNegation.class)) {
        negatedAfterTemplates.add(((ExpressionTemplate)afterTemplate).negation());
      }
    }
    if (!negatedAfterTemplates.isEmpty()) {
      List<ExpressionTemplate> negatedBeforeTemplates=new ArrayList<>();
      for (      Template<?> beforeTemplate : beforeTemplates) {
        negatedBeforeTemplates.add(((ExpressionTemplate)beforeTemplate).negation());
      }
      RefasterRule<?,?> negation=RefasterRule.create(qualifiedTemplateClass,typeVars,negatedBeforeTemplates,negatedAfterTemplates,annotationMap);
      return ImmutableList.of(rule,negation);
    }
    return ImmutableList.of(rule);
  }
}
