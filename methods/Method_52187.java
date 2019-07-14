@Override String kindDisplayName(ASTMethodDeclaration node,PropertyDescriptor<Pattern> descriptor){
  return DESCRIPTOR_TO_DISPLAY_NAME.get(descriptor.name());
}
