@Override public JavaTypeDefinition getJavaType(int index){
  if (index == 0) {
    return this;
  }
 else {
    throw new IllegalArgumentException("Not an intersection type!");
  }
}
