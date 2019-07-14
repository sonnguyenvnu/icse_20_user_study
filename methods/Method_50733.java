private void validateStaticIVorKey(ASTMethodCallExpression methodCall,Object data){
  int numberOfChildren=methodCall.jjtGetNumChildren();
switch (numberOfChildren) {
case 5:
    Object potentialIV=methodCall.jjtGetChild(3);
  reportIfHardCoded(data,potentialIV);
Object potentialKey=methodCall.jjtGetChild(2);
reportIfHardCoded(data,potentialKey);
break;
case 4:
Object key=methodCall.jjtGetChild(2);
reportIfHardCoded(data,key);
break;
default :
break;
}
}
