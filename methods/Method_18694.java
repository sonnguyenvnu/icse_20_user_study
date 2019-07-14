@NotNull @Override protected List<? extends GenerationInfo> generateMemberPrototypes(PsiClass aClass,ClassMember[] members) throws IncorrectOperationException {
  return Collections.singletonList(new PsiGenerationInfo<PsiMember>(generatedMethod));
}
