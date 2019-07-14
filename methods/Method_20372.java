private ControllerModelField buildFieldInfo(Element modelFieldElement){
  validateFieldAccessibleViaGeneratedCode(modelFieldElement,AutoModel.class,errorLogger);
  TypeMirror fieldType=modelFieldElement.asType();
  if (fieldType.getKind() != TypeKind.ERROR) {
    if (!isEpoxyModel(fieldType)) {
      errorLogger.logError("Fields with %s annotations must be of type %s (%s#%s)",AutoModel.class.getSimpleName(),EPOXY_MODEL_TYPE,modelFieldElement.getEnclosingElement().getSimpleName(),modelFieldElement.getSimpleName());
    }
  }
  return new ControllerModelField(modelFieldElement);
}
