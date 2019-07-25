@NotNull private Collection<TemplateModule> build(){
  myBadLanguages.clear();
  myEngagedTrace.clear();
  final Collection<SLanguage> initialLanguages=getAllLanguages();
  Queue<EngagedLanguage> queue=new ArrayDeque<>(resolveLanguages(initialLanguages,null,null));
  Set<String> processedLanguages=new HashSet<>(toQualifiedName(initialLanguages));
  Set<EngagedLanguage> participatingLanguages=new HashSet<>(queue);
  while (!queue.isEmpty()) {
    EngagedLanguage next=queue.remove();
    for (    LanguageRuntime extendedLang : next.getLanguage().getExtendedLanguages()) {
      if (processedLanguages.add(extendedLang.getNamespace())) {
        final EngagedLanguage engaged=new EngagedLanguage(extendedLang,next,"EXTENDS");
        participatingLanguages.add(engaged);
        queue.add(engaged);
      }
    }
    HashSet<EngagedLanguage> targetLanguages=new HashSet<>();
    myEngagedTrace.addAll(collectGeneratorsAndTargetLanguages(next,targetLanguages));
    for (    EngagedLanguage t : targetLanguages) {
      if (processedLanguages.add(t.getName())) {
        participatingLanguages.add(t);
        queue.add(t);
      }
    }
  }
  ArrayList<TemplateModule> all=new ArrayList<>();
  HashSet<SModuleReference> processedGenerators=new HashSet<>(myEngagedTrace.size() * 2);
  for (  EngagedGenerator m : myEngagedTrace) {
    final TemplateModule tm=m.getGenerator();
    if (processedGenerators.add(tm.getModuleReference())) {
      all.add(tm);
    }
  }
  return Collections.unmodifiableList(all);
}
