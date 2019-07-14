static List<SpecModelValidationError> validateShouldUseDisplayLists(MountSpecModel specModel){
  List<SpecModelValidationError> validationErrors=new ArrayList<>();
  if (specModel.shouldUseDisplayList() && !specModel.getMountType().equals(ClassNames.COMPONENT_LIFECYCLE_MOUNT_TYPE_DRAWABLE)) {
    validationErrors.add(new SpecModelValidationError(specModel.getRepresentedObject(),"shouldUseDisplayList = true can only be used on MountSpecs that mount a drawable."));
  }
  return validationErrors;
}
