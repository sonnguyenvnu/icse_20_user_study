@InternalApi @Deprecated @Override public void setType(Class<?> type){
  typeDefinition=JavaTypeDefinition.forClass(type);
}
