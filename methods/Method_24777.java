static protected ASTNode definedIn(ASTNode node,String name,ArrayList<Integer> constrains){
  if (node == null)   return null;
  if (constrains != null) {
    if (!constrains.contains(node.getNodeType()) && constrains.size() > 0) {
      return null;
    }
  }
  List<VariableDeclarationFragment> vdfList=null;
switch (node.getNodeType()) {
case ASTNode.TYPE_DECLARATION:
    TypeDeclaration td=(TypeDeclaration)node;
  if (td.getName().toString().equals(name)) {
    if (constrains.contains(ASTNode.CLASS_INSTANCE_CREATION)) {
      MethodDeclaration[] methods=td.getMethods();
      for (      MethodDeclaration md : methods) {
        if (md.getName().toString().equalsIgnoreCase(name)) {
          log("Found a constructor.");
          return md;
        }
      }
    }
 else {
      return node;
    }
  }
 else {
    if (constrains.contains(ASTNode.FIELD_DECLARATION)) {
      FieldDeclaration[] fields=td.getFields();
      for (      FieldDeclaration fd : fields) {
        List<VariableDeclarationFragment> fragments=fd.fragments();
        for (        VariableDeclarationFragment vdf : fragments) {
          if (vdf.getName().toString().equalsIgnoreCase(name))           return fd;
        }
      }
    }
 else     if (constrains.contains(ASTNode.METHOD_DECLARATION)) {
      MethodDeclaration[] methods=td.getMethods();
      for (      MethodDeclaration md : methods) {
        if (md.getName().toString().equalsIgnoreCase(name)) {
          return md;
        }
      }
    }
  }
break;
case ASTNode.METHOD_DECLARATION:
if (((MethodDeclaration)node).getName().toString().equalsIgnoreCase(name)) return node;
break;
case ASTNode.SINGLE_VARIABLE_DECLARATION:
if (((SingleVariableDeclaration)node).getName().toString().equalsIgnoreCase(name)) return node;
break;
case ASTNode.FIELD_DECLARATION:
vdfList=((FieldDeclaration)node).fragments();
break;
case ASTNode.VARIABLE_DECLARATION_EXPRESSION:
vdfList=((VariableDeclarationExpression)node).fragments();
break;
case ASTNode.VARIABLE_DECLARATION_STATEMENT:
vdfList=((VariableDeclarationStatement)node).fragments();
break;
default :
}
if (vdfList != null) {
for (VariableDeclarationFragment vdf : vdfList) {
if (vdf.getName().toString().equalsIgnoreCase(name)) return node;
}
}
return null;
}
