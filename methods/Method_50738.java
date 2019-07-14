private void collectCRUDMethodLevelChecks(final ASTMethodCallExpression node){
  final String method=node.getNode().getMethodName();
  final ASTReferenceExpression ref=node.getFirstChildOfType(ASTReferenceExpression.class);
  if (ref == null) {
    return;
  }
  List<Identifier> a=ref.getNode().getNames();
  if (!a.isEmpty()) {
    extractObjectAndFields(a,method,node.getNode().getDefiningType().getApexName());
  }
 else {
    if (Helper.isMethodCallChain(node,ESAPI_ISAUTHORIZED_TO_VIEW)) {
      extractObjectTypeFromESAPI(node,IS_ACCESSIBLE);
    }
    if (Helper.isMethodCallChain(node,ESAPI_ISAUTHORIZED_TO_CREATE)) {
      extractObjectTypeFromESAPI(node,IS_CREATEABLE);
    }
    if (Helper.isMethodCallChain(node,ESAPI_ISAUTHORIZED_TO_UPDATE)) {
      extractObjectTypeFromESAPI(node,IS_UPDATEABLE);
    }
    if (Helper.isMethodCallChain(node,ESAPI_ISAUTHORIZED_TO_DELETE)) {
      extractObjectTypeFromESAPI(node,IS_DELETABLE);
    }
    final ASTMethodCallExpression nestedMethodCall=ref.getFirstChildOfType(ASTMethodCallExpression.class);
    if (nestedMethodCall != null) {
      if (isLastMethodName(nestedMethodCall,S_OBJECT_TYPE,GET_DESCRIBE)) {
        String resolvedType=getType(nestedMethodCall);
        if (!typeToDMLOperationMapping.get(resolvedType).contains(method)) {
          typeToDMLOperationMapping.put(resolvedType,method);
        }
      }
    }
  }
}
