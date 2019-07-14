private void dump(JavaNode node,String prefix){
  writer.print(prefix);
  writer.print(node.getXPathNodeName());
  String image=node.getImage();
  if (node instanceof ASTBooleanLiteral) {
    image=String.valueOf(((ASTBooleanLiteral)node).isTrue());
  }
 else   if (node instanceof ASTPrimaryPrefix) {
    ASTPrimaryPrefix primaryPrefix=(ASTPrimaryPrefix)node;
    String result=null;
    if (primaryPrefix.usesSuperModifier()) {
      result="super";
    }
 else     if (primaryPrefix.usesThisModifier()) {
      result="this";
    }
    if (image != null) {
      result+="." + image;
    }
    image=result;
  }
 else   if (node instanceof ASTPrimarySuffix) {
    ASTPrimarySuffix primarySuffix=(ASTPrimarySuffix)node;
    if (primarySuffix.isArrayDereference()) {
      if (image == null) {
        image="[";
      }
 else {
        image="[" + image;
      }
    }
  }
  List<String> extras=new ArrayList<>();
  collectModifiers(node,extras);
  if (node instanceof Dimensionable) {
    Dimensionable dimensionable=(Dimensionable)node;
    if (dimensionable.isArray()) {
      StringBuilder extra=new StringBuilder("array");
      for (int i=0; i < dimensionable.getArrayDepth(); i++) {
        extra.append('[');
      }
      extras.add(extra.toString());
    }
  }
  if (node instanceof ASTArguments) {
    extras.add(String.valueOf(((ASTArguments)node).getArgumentCount()));
  }
 else   if (node instanceof ASTAssignmentOperator) {
    extras.add(((ASTAssignmentOperator)node).isCompound() ? "compound" : "simple");
  }
 else   if (node instanceof ASTClassOrInterfaceBodyDeclaration) {
    if (((ASTClassOrInterfaceBodyDeclaration)node).isAnonymousInnerClass()) {
      extras.add("anonymous inner class");
    }
    if (((ASTClassOrInterfaceBodyDeclaration)node).isEnumChild()) {
      extras.add("enum child");
    }
  }
 else   if (node instanceof ASTBlock) {
    if (((ASTBlock)node).containsComment()) {
      extras.add("contains comment");
    }
  }
 else   if (node instanceof ASTClassOrInterfaceDeclaration) {
    extras.add(((ASTClassOrInterfaceDeclaration)node).isInterface() ? "interface" : "class");
    if (((ASTClassOrInterfaceDeclaration)node).isNested()) {
      extras.add("nested");
    }
  }
 else   if (node instanceof ASTConditionalExpression) {
    extras.add("ternary");
  }
 else   if (node instanceof ASTConstructorDeclaration) {
    extras.add(String.valueOf(((ASTConstructorDeclaration)node).getParameterCount()));
    if (((ASTConstructorDeclaration)node).containsComment()) {
      extras.add("contains comment");
    }
  }
 else   if (node instanceof ASTExplicitConstructorInvocation) {
    extras.add(String.valueOf(((ASTExplicitConstructorInvocation)node).getArgumentCount()));
    if (((ASTExplicitConstructorInvocation)node).isThis()) {
      extras.add("this");
    }
    if (((ASTExplicitConstructorInvocation)node).isSuper()) {
      extras.add("super");
    }
  }
 else   if (node instanceof ASTFormalParameter) {
    if (((ASTFormalParameter)node).isVarargs()) {
      extras.add("varargs");
    }
  }
 else   if (node instanceof ASTFormalParameters) {
    extras.add(String.valueOf(((ASTFormalParameters)node).getParameterCount()));
  }
 else   if (node instanceof ASTIfStatement) {
    if (((ASTIfStatement)node).hasElse()) {
      extras.add("has else");
    }
  }
 else   if (node instanceof ASTImportDeclaration) {
    if (((ASTImportDeclaration)node).isImportOnDemand()) {
      extras.add("on demand");
    }
    if (((ASTImportDeclaration)node).isStatic()) {
      extras.add("static");
    }
  }
 else   if (node instanceof ASTInitializer) {
    extras.add(((ASTInitializer)node).isStatic() ? "static" : "nonstatic");
  }
 else   if (node instanceof ASTLiteral) {
    ASTLiteral literal=(ASTLiteral)node;
    if (literal.isCharLiteral()) {
      extras.add("char style");
    }
    if (literal.isIntLiteral()) {
      extras.add("int style");
    }
    if (literal.isFloatLiteral()) {
      extras.add("float style");
    }
    if (literal.isStringLiteral()) {
      extras.add("String style");
    }
    if (literal.isDoubleLiteral()) {
      extras.add("double style");
    }
    if (literal.isLongLiteral()) {
      extras.add("long style");
    }
  }
 else   if (node instanceof ASTResultType) {
    if (((ASTResultType)node).isVoid()) {
      extras.add("void");
    }
    if (((ASTResultType)node).returnsArray()) {
      extras.add("returns array");
    }
  }
 else   if (node instanceof ASTSwitchLabel) {
    if (((ASTSwitchLabel)node).isDefault()) {
      extras.add("default");
    }
  }
 else   if (node instanceof ASTTryStatement) {
    if (((ASTTryStatement)node).hasFinally()) {
      extras.add("has finally");
    }
  }
 else   if (node instanceof ASTModuleDirective) {
    ASTModuleDirective directive=(ASTModuleDirective)node;
    extras.add(directive.getType());
    if (directive.getRequiresModifier() != null) {
      extras.add(directive.getRequiresModifier());
    }
  }
  if (image != null || !extras.isEmpty()) {
    writer.print(':');
    if (image != null) {
      writer.print(image);
    }
    for (    String extra : extras) {
      writer.print('(');
      writer.print(extra);
      writer.print(')');
    }
  }
  writer.println();
}
