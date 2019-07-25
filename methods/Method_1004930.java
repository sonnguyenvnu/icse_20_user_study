/** 
 * Validates an operation. This should be used to validate that fields have been be configured correctly. By default no validation is applied. Override this method to implement validation.
 * @return validation result.
 */
default ValidationResult validate(){
  final ValidationResult result=new ValidationResult();
  HashSet<Field> fields=Sets.<Field>newHashSet();
  Class<?> currentClass=this.getClass();
  while (null != currentClass) {
    fields.addAll(Arrays.asList(currentClass.getDeclaredFields()));
    currentClass=currentClass.getSuperclass();
  }
  for (  final Field field : fields) {
    final Required[] annotations=field.getAnnotationsByType(Required.class);
    if (null != annotations && annotations.length > 0) {
      if (field.isAccessible()) {
        validateRequiredFieldPresent(result,field);
      }
 else {
        AccessController.doPrivileged((PrivilegedAction<Operation>)() -> {
          field.setAccessible(true);
          validateRequiredFieldPresent(result,field);
          return null;
        }
);
      }
    }
  }
  return result;
}
