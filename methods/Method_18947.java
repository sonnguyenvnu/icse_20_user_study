private static void validateDuplicateName(Set<String> stateNameSet,List<? extends MethodParamModel> propModelList,List<SpecModelValidationError> validationErrors){
  for (int i=0, size=propModelList.size(); i < size; i++) {
    final MethodParamModel model=propModelList.get(i);
    if (stateNameSet.contains(model.getName())) {
      final Annotation paramAnnotation=model.getAnnotations().stream().filter(it -> it instanceof Prop || it instanceof InjectProp || it instanceof TreeProp).findFirst().get();
      validationErrors.add(new SpecModelValidationError(model.getRepresentedObject(),"The parameter name of @" + paramAnnotation.annotationType().getSimpleName() + " \"" + model.getName() + "\" and @State \"" + model.getName() + "\" collide!"));
    }
  }
}
