/** 
 * Collect annotations for some target.
 */
@SuppressWarnings({"unchecked"}) protected void collectAnnotationChecks(final List<Check> annChecks,final Class targetType,final String targetName,final Annotation[] annotations){
  for (  Annotation annotation : annotations) {
    Constraint c=annotation.annotationType().getAnnotation(Constraint.class);
    Class<? extends ValidationConstraint> constraintClass;
    if (c == null) {
      String constraintClassName=annotation.annotationType().getName() + "Constraint";
      try {
        constraintClass=ClassLoaderUtil.loadClass(constraintClassName,this.getClass().getClassLoader());
      }
 catch (      ClassNotFoundException ingore) {
        continue;
      }
    }
 else {
      constraintClass=c.value();
    }
    ValidationConstraint vc;
    try {
      vc=newConstraint(constraintClass,targetType);
    }
 catch (    Exception ex) {
      throw new VtorException("Invalid constraint: " + constraintClass.getClass().getName(),ex);
    }
    vc.configure(annotation);
    Check check=new Check(targetName,vc);
    copyDefaultCheckProperties(check,annotation);
    annChecks.add(check);
  }
}
