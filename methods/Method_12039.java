private Object createTestUsingFieldInjection() throws Exception {
  List<FrameworkField> annotatedFieldsByParameter=getAnnotatedFieldsByParameter();
  if (annotatedFieldsByParameter.size() != parameters.length) {
    throw new Exception("Wrong number of parameters and @Parameter fields." + " @Parameter fields counted: " + annotatedFieldsByParameter.size() + ", available parameters: " + parameters.length + ".");
  }
  Object testClassInstance=getTestClass().getJavaClass().newInstance();
  for (  FrameworkField each : annotatedFieldsByParameter) {
    Field field=each.getField();
    Parameter annotation=field.getAnnotation(Parameter.class);
    int index=annotation.value();
    try {
      field.set(testClassInstance,parameters[index]);
    }
 catch (    IllegalAccessException e) {
      IllegalAccessException wrappedException=new IllegalAccessException("Cannot set parameter '" + field.getName() + "'. Ensure that the field '" + field.getName() + "' is public.");
      wrappedException.initCause(e);
      throw wrappedException;
    }
catch (    IllegalArgumentException iare) {
      throw new Exception(getTestClass().getName() + ": Trying to set " + field.getName() + " with the value " + parameters[index] + " that is not the right type (" + parameters[index].getClass().getSimpleName() + " instead of " + field.getType().getSimpleName() + ").",iare);
    }
  }
  return testClassInstance;
}
