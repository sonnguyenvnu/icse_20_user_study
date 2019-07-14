private String getClassNames(Class<?>... testClasses){
  final StringBuilder builder=new StringBuilder();
  for (  Class<?> testClass : testClasses) {
    if (builder.length() != 0) {
      builder.append(", ");
    }
    builder.append(testClass.getName());
  }
  return builder.toString();
}
