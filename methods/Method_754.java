protected Class<?> getDeclaredClass(){
  if (this.method != null) {
    return this.method.getDeclaringClass();
  }
  if (this.field != null) {
    return this.field.getDeclaringClass();
  }
  return null;
}
