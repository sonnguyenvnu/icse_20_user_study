/** 
 * A variation of findDeclaration() but accepts an alternate parent ASTNode
 * @param findMe
 * @param alternateParent
 * @return
 */
protected static ASTNode findDeclaration2(Name findMe,ASTNode alternateParent){
  ASTNode declaringClass;
  ASTNode parent=findMe.getParent();
  ASTNode ret;
  ArrayList<Integer> constrains=new ArrayList<>();
  if (parent.getNodeType() == ASTNode.METHOD_INVOCATION) {
    Expression exp=(Expression)parent.getStructuralProperty(MethodInvocation.EXPRESSION_PROPERTY);
    if (((MethodInvocation)parent).getName().toString().equals(findMe.toString())) {
      constrains.add(ASTNode.METHOD_DECLARATION);
      if (exp != null) {
        constrains.add(ASTNode.TYPE_DECLARATION);
        if (exp instanceof MethodInvocation) {
          SimpleType stp=extracTypeInfo(findDeclaration2(((MethodInvocation)exp).getName(),alternateParent));
          if (stp == null)           return null;
          declaringClass=findDeclaration2(stp.getName(),alternateParent);
          return definedIn(declaringClass,((MethodInvocation)parent).getName().toString(),constrains);
        }
 else         if (exp instanceof FieldAccess) {
          SimpleType stp=extracTypeInfo(findDeclaration2(((FieldAccess)exp).getName(),alternateParent));
          if (stp == null)           return null;
          declaringClass=findDeclaration2((stp.getName()),alternateParent);
          return definedIn(declaringClass,((MethodInvocation)parent).getName().toString(),constrains);
        }
        if (exp instanceof SimpleName) {
          SimpleType stp=extracTypeInfo(findDeclaration2(((SimpleName)exp),alternateParent));
          if (stp == null)           return null;
          declaringClass=findDeclaration2(stp.getName(),alternateParent);
          constrains.add(ASTNode.METHOD_DECLARATION);
          return definedIn(declaringClass,((MethodInvocation)parent).getName().toString(),constrains);
        }
      }
    }
 else {
      parent=parent.getParent();
      alternateParent=alternateParent.getParent();
    }
  }
 else   if (parent.getNodeType() == ASTNode.FIELD_ACCESS) {
    FieldAccess fa=(FieldAccess)parent;
    Expression exp=fa.getExpression();
    if (fa.getName().toString().equals(findMe.toString())) {
      constrains.add(ASTNode.FIELD_DECLARATION);
      if (exp != null) {
        constrains.add(ASTNode.TYPE_DECLARATION);
        if (exp instanceof MethodInvocation) {
          SimpleType stp=extracTypeInfo(findDeclaration2(((MethodInvocation)exp).getName(),alternateParent));
          if (stp == null)           return null;
          declaringClass=findDeclaration2(stp.getName(),alternateParent);
          return definedIn(declaringClass,fa.getName().toString(),constrains);
        }
 else         if (exp instanceof FieldAccess) {
          SimpleType stp=extracTypeInfo(findDeclaration2(((FieldAccess)exp).getName(),alternateParent));
          if (stp == null)           return null;
          declaringClass=findDeclaration2((stp.getName()),alternateParent);
          constrains.add(ASTNode.TYPE_DECLARATION);
          return definedIn(declaringClass,fa.getName().toString(),constrains);
        }
        if (exp instanceof SimpleName) {
          SimpleType stp=extracTypeInfo(findDeclaration2(((SimpleName)exp),alternateParent));
          if (stp == null)           return null;
          declaringClass=findDeclaration2(stp.getName(),alternateParent);
          constrains.add(ASTNode.METHOD_DECLARATION);
          return definedIn(declaringClass,fa.getName().toString(),constrains);
        }
      }
    }
 else {
      parent=parent.getParent();
      alternateParent=alternateParent.getParent();
    }
  }
 else   if (parent.getNodeType() == ASTNode.QUALIFIED_NAME) {
    QualifiedName qn=(QualifiedName)parent;
    if (!findMe.toString().equals(qn.getQualifier().toString())) {
      SimpleType stp=extracTypeInfo(findDeclaration2((qn.getQualifier()),alternateParent));
      if (stp == null)       return null;
      declaringClass=findDeclaration2(stp.getName(),alternateParent);
      constrains.clear();
      constrains.add(ASTNode.TYPE_DECLARATION);
      constrains.add(ASTNode.FIELD_DECLARATION);
      return definedIn(declaringClass,qn.getName().toString(),constrains);
    }
 else {
      if (findMe instanceof QualifiedName) {
        QualifiedName qnn=(QualifiedName)findMe;
        SimpleType stp=extracTypeInfo(findDeclaration2((qnn.getQualifier()),alternateParent));
        if (stp == null) {
          return null;
        }
        declaringClass=findDeclaration2(stp.getName(),alternateParent);
        constrains.clear();
        constrains.add(ASTNode.TYPE_DECLARATION);
        constrains.add(ASTNode.FIELD_DECLARATION);
        return definedIn(declaringClass,qnn.getName().toString(),constrains);
      }
    }
  }
 else   if (parent.getNodeType() == ASTNode.SIMPLE_TYPE) {
    constrains.add(ASTNode.TYPE_DECLARATION);
    if (parent.getParent().getNodeType() == ASTNode.CLASS_INSTANCE_CREATION)     constrains.add(ASTNode.CLASS_INSTANCE_CREATION);
  }
 else   if (parent instanceof Expression) {
  }
  while (alternateParent != null) {
    for (    Object oprop : alternateParent.structuralPropertiesForType()) {
      StructuralPropertyDescriptor prop=(StructuralPropertyDescriptor)oprop;
      if (prop.isChildProperty() || prop.isSimpleProperty()) {
        if (alternateParent.getStructuralProperty(prop) instanceof ASTNode) {
          ret=definedIn((ASTNode)alternateParent.getStructuralProperty(prop),findMe.toString(),constrains);
          if (ret != null)           return ret;
        }
      }
 else       if (prop.isChildListProperty()) {
        List<ASTNode> nodelist=(List<ASTNode>)alternateParent.getStructuralProperty(prop);
        for (        ASTNode retNode : nodelist) {
          ret=definedIn(retNode,findMe.toString(),constrains);
          if (ret != null)           return ret;
        }
      }
    }
    alternateParent=alternateParent.getParent();
  }
  return null;
}
