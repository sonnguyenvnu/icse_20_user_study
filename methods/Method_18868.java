private static List<SpecModelValidationError> validateHasNoDynamicProps(SpecModel specModel){
  final List<SpecModelValidationError> validationErrors=new ArrayList<>();
  for (  PropModel dynamicProp : SpecModelUtils.getDynamicProps(specModel)) {
    validationErrors.add(new SpecModelValidationError(dynamicProp.getRepresentedObject(),specModel.getSpecName() + " declares dynamic props " + dynamicProp.getName() + " (only MountSpecs support dynamic props)."));
  }
  if (specModel.getRepresentedObject() instanceof TypeElement) {
    final TypeElement spec=(TypeElement)specModel.getRepresentedObject();
    for (    Element enclosedElement : spec.getEnclosedElements()) {
      if (enclosedElement.getKind() != ElementKind.METHOD || enclosedElement.getAnnotation(OnBindDynamicValue.class) == null) {
        continue;
      }
      validationErrors.add(new SpecModelValidationError(enclosedElement,specModel.getSpecName() + " declares " + OnBindDynamicValue.class + " methods " + specModel.getSpecName() + " (only MountSpecs support dynamic props)."));
    }
  }
  return validationErrors;
}
