public JSONField getAnnotation(){
  if (this.fieldAnnotation != null) {
    return this.fieldAnnotation;
  }
  return this.methodAnnotation;
}
