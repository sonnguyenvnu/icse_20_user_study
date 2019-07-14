private void validateAnnotationOptions(ErrorLogger errorLogger,EpoxyAttribute annotation,Set<Option> options){
  if (options.contains(Option.IgnoreRequireHashCode) && options.contains(Option.DoNotHash)) {
    errorLogger.logError("Illegal to use both %s and %s options in an %s annotation. (%s#%s)",Option.DoNotHash,Option.IgnoreRequireHashCode,EpoxyAttribute.class.getSimpleName(),classElement.getSimpleName(),getFieldName());
  }
  if (!options.isEmpty()) {
    if (!annotation.hash()) {
      errorLogger.logError("Don't use hash=false in an %s if you are using options. Instead, use the" + " %s option. (%s#%s)",EpoxyAttribute.class.getSimpleName(),Option.DoNotHash,classElement.getSimpleName(),getFieldName());
    }
    if (!annotation.setter()) {
      errorLogger.logError("Don't use setter=false in an %s if you are using options. Instead, use the" + " %s option. (%s#%s)",EpoxyAttribute.class.getSimpleName(),Option.NoSetter,classElement.getSimpleName(),getFieldName());
    }
  }
}
