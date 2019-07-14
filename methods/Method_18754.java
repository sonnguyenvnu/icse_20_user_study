public static ImmutableList<WorkingRangeMethodModel> getRangesMethods(PsiClass psiClass,List<Class<? extends Annotation>> permittedInterStageInputAnnotations){
  final List<WorkingRangeMethodModel> workingRangeMethods=new ArrayList<>();
  for (  PsiMethod psiMethod : psiClass.getMethods()) {
    final OnEnteredRange enteredRangeAnnotation=AnnotationUtil.findAnnotationInHierarchy(psiMethod,OnEnteredRange.class);
    final OnExitedRange exitedRangeAnnotation=AnnotationUtil.findAnnotationInHierarchy(psiMethod,OnExitedRange.class);
    if (enteredRangeAnnotation != null) {
      SpecMethodModel<EventMethod,WorkingRangeDeclarationModel> enteredRangeMethod=generateWorkingRangeMethod(psiMethod,permittedInterStageInputAnnotations,"OnEnteredRange");
      final String name=enteredRangeAnnotation.name();
      final WorkingRangeMethodModel workingRangeModel=workingRangeMethods.stream().filter(it -> it.name.equals(name) && it.enteredRangeModel == null).findFirst().orElseGet(() -> {
        WorkingRangeMethodModel model=new WorkingRangeMethodModel(name);
        workingRangeMethods.add(model);
        return model;
      }
);
      workingRangeModel.enteredRangeModel=enteredRangeMethod;
    }
    if (exitedRangeAnnotation != null) {
      SpecMethodModel<EventMethod,WorkingRangeDeclarationModel> exitedRangeMethod=generateWorkingRangeMethod(psiMethod,permittedInterStageInputAnnotations,"OnExitedRange");
      final String name=exitedRangeAnnotation.name();
      final WorkingRangeMethodModel workingRangeModel=workingRangeMethods.stream().filter(it -> it.name.equals(name) && it.exitedRangeModel == null).findFirst().orElseGet(() -> {
        WorkingRangeMethodModel model=new WorkingRangeMethodModel(name);
        workingRangeMethods.add(model);
        return model;
      }
);
      workingRangeModel.exitedRangeModel=exitedRangeMethod;
    }
  }
  return ImmutableList.copyOf(workingRangeMethods);
}
