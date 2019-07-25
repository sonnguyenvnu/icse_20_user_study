@Override public NewInstance clone(){
  return new NewInstance(qualifier != null ? qualifier.clone() : null,constructorMethodDescriptor,AstUtils.clone(arguments));
}
