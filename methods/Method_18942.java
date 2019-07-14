static List<SpecModelValidationError> validateGetMountType(MountSpecModel specModel){
  List<SpecModelValidationError> validationErrors=new ArrayList<>();
  if (!specModel.getMountType().equals(ClassNames.COMPONENT_LIFECYCLE_MOUNT_TYPE_DRAWABLE) && !specModel.getMountType().equals(ClassNames.COMPONENT_LIFECYCLE_MOUNT_TYPE_VIEW)) {
    validationErrors.add(new SpecModelValidationError(specModel.getRepresentedObject(),"onCreateMountContent's return type should be either a View or a Drawable " + "subclass."));
  }
  return validationErrors;
}
