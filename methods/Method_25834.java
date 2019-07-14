private static boolean isOnThis(MethodInvocationTree tree){
  ExpressionTree receiver=ASTHelpers.getReceiver(tree);
  if (receiver == null) {
    return true;
  }
  Name receiverName;
switch (receiver.getKind()) {
case IDENTIFIER:
    receiverName=((IdentifierTree)receiver).getName();
  break;
case MEMBER_SELECT:
receiverName=((MemberSelectTree)receiver).getIdentifier();
break;
default :
return false;
}
return receiverName.contentEquals("this") || receiverName.contentEquals("super");
}
