private void checkCommentMeetsRequirement(Object data,AbstractJavaNode node,PropertyDescriptor<CommentRequirement> descriptor){
switch (getProperty(descriptor)) {
case Ignored:
    break;
case Required:
  if (node.comment() == null) {
    commentRequiredViolation(data,node,descriptor);
  }
break;
case Unwanted:
if (node.comment() != null) {
commentRequiredViolation(data,node,descriptor);
}
break;
default :
break;
}
}
