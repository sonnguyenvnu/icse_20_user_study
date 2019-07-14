private void commentRequiredViolation(Object data,AbstractJavaNode node,PropertyDescriptor<CommentRequirement> descriptor){
  addViolationWithMessage(data,node,DESCRIPTOR_NAME_TO_COMMENT_TYPE.get(descriptor.name()) + " are " + getProperty(descriptor).label.toLowerCase(Locale.ROOT));
}
