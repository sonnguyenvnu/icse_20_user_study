private void validateIterableType(DeclaredType declaredType) throws EpoxyProcessorException {
  for (  TypeMirror typeParameter : declaredType.getTypeArguments()) {
    try {
      validateImplementsHashCode(typeParameter);
    }
 catch (    EpoxyProcessorException e) {
      throwError("Type in Iterable does not implement hashCode. Type: %s",typeParameter.toString());
    }
  }
}
