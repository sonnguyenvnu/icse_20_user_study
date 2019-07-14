/** 
 * Finds the type of the expression in foo.bar().a().b, this would give me the type of b if it exists in return type of a(). If noCompare is true, it'll return type of a()
 * @param nearestNode
 * @param astNode
 * @return
 */
public static ClassMember resolveExpression3rdParty(PreprocessedSketch ps,ASTNode nearestNode,ASTNode astNode,boolean noCompare){
  log("Resolve 3rdParty expr-- " + getNodeAsString(astNode) + " nearest node " + getNodeAsString(nearestNode));
  if (astNode == null)   return null;
  ClassMember scopeParent;
  SimpleType stp;
  if (astNode instanceof SimpleName) {
    ASTNode decl=findDeclaration2(((SimpleName)astNode),nearestNode);
    if (decl != null) {
      log(getNodeAsString(astNode) + " found decl -> " + getNodeAsString(decl));
{
        if (decl.getNodeType() == ASTNode.TYPE_DECLARATION) {
          TypeDeclaration td=(TypeDeclaration)decl;
          return new ClassMember(ps,td);
        }
      }
{
        Type type=extracTypeInfo2(decl);
        if (type != null && type.isArrayType() && astNode.getParent().getNodeType() != ASTNode.ARRAY_ACCESS) {
          Type elementType=((ArrayType)type).getElementType();
          String name="";
          if (elementType.isSimpleType()) {
            Class<?> c=findClassIfExists(ps,elementType.toString());
            if (c != null)             name=c.getName();
          }
 else           if (elementType.isPrimitiveType()) {
            name=((PrimitiveType)elementType).getPrimitiveTypeCode().toString();
          }
          Class<?> arrayClass=getArrayClass(name,ps.classLoader);
          return arrayClass == null ? null : new ClassMember(arrayClass);
        }
      }
      return new ClassMember(ps,extracTypeInfo(decl));
    }
 else {
      Class<?> tehClass=findClassIfExists(ps,astNode.toString());
      if (tehClass != null) {
        return new ClassMember(tehClass);
      }
    }
    astNode=astNode.getParent();
  }
switch (astNode.getNodeType()) {
case ASTNode.FIELD_ACCESS:
    FieldAccess fa=(FieldAccess)astNode;
  if (fa.getExpression() == null) {
    log("FA,Not implemented.");
    return null;
  }
 else {
    if (fa.getExpression() instanceof SimpleName) {
      stp=extracTypeInfo(findDeclaration2((SimpleName)fa.getExpression(),nearestNode));
      if (stp == null) {
        Class<?> tehClass=findClassIfExists(ps,fa.getExpression().toString());
        if (tehClass != null) {
          return definedIn3rdPartyClass(ps,new ClassMember(tehClass),fa.getName().toString());
        }
        log("FA resolve 3rd par, Can't resolve " + fa.getExpression());
        return null;
      }
      log("FA, SN Type " + getNodeAsString(stp));
      scopeParent=definedIn3rdPartyClass(ps,stp.getName().toString(),"THIS");
    }
 else {
      scopeParent=resolveExpression3rdParty(ps,nearestNode,fa.getExpression(),noCompare);
    }
    log("FA, ScopeParent " + scopeParent);
    return definedIn3rdPartyClass(ps,scopeParent,fa.getName().toString());
  }
case ASTNode.METHOD_INVOCATION:
MethodInvocation mi=(MethodInvocation)astNode;
ASTNode temp=findDeclaration2(mi.getName(),nearestNode);
if (temp instanceof MethodDeclaration) {
log(mi.getName() + " was found locally," + getNodeAsString(extracTypeInfo(temp)));
{
Type type=extracTypeInfo2(temp);
if (type != null && type.isArrayType() && astNode.getParent().getNodeType() != ASTNode.ARRAY_ACCESS) {
  Type elementType=((ArrayType)type).getElementType();
  String name="";
  if (elementType.isSimpleType()) {
    Class<?> c=findClassIfExists(ps,elementType.toString());
    if (c != null)     name=c.getName();
  }
 else   if (elementType.isPrimitiveType()) {
    name=((PrimitiveType)elementType).getPrimitiveTypeCode().toString();
  }
  Class<?> arrayClass=getArrayClass(name,ps.classLoader);
  return arrayClass == null ? null : new ClassMember(arrayClass);
}
}
return new ClassMember(ps,extracTypeInfo(temp));
}
if (mi.getExpression() == null) {
log("MI,Not implemented.");
return null;
}
 else {
if (mi.getExpression() instanceof SimpleName) {
ASTNode decl=findDeclaration2((SimpleName)mi.getExpression(),nearestNode);
if (decl != null) {
if (decl.getNodeType() == ASTNode.TYPE_DECLARATION) {
  TypeDeclaration td=(TypeDeclaration)decl;
  return new ClassMember(ps,td);
}
stp=extracTypeInfo(decl);
if (stp == null) {
  Class<?> tehClass=findClassIfExists(ps,mi.getExpression().toString());
  if (tehClass != null) {
    return definedIn3rdPartyClass(ps,new ClassMember(tehClass),mi.getName().toString());
  }
  log("MI resolve 3rd par, Can't resolve " + mi.getExpression());
  return null;
}
log("MI, SN Type " + getNodeAsString(stp));
ASTNode typeDec=findDeclaration2(stp.getName(),nearestNode);
if (typeDec == null) {
  log(stp.getName() + " couldn't be found locally..");
  Class<?> tehClass=findClassIfExists(ps,stp.getName().toString());
  if (tehClass != null) {
    return definedIn3rdPartyClass(ps,new ClassMember(tehClass),mi.getName().toString());
  }
}
return definedIn3rdPartyClass(ps,new ClassMember(ps,typeDec),mi.getName().toString());
}
}
 else {
log("MI EXP.." + getNodeAsString(mi.getExpression()));
scopeParent=resolveExpression3rdParty(ps,nearestNode,mi.getExpression(),noCompare);
log("MI, ScopeParent " + scopeParent);
return definedIn3rdPartyClass(ps,scopeParent,mi.getName().toString());
}
}
break;
case ASTNode.QUALIFIED_NAME:
QualifiedName qn=(QualifiedName)astNode;
ASTNode temp2=findDeclaration2(qn.getName(),nearestNode);
if (temp2 instanceof FieldDeclaration) {
log(qn.getName() + " was found locally," + getNodeAsString(extracTypeInfo(temp2)));
return new ClassMember(ps,extracTypeInfo(temp2));
}
if (qn.getQualifier() == null) {
log("QN,Not implemented.");
return null;
}
 else {
if (qn.getQualifier() instanceof SimpleName) {
stp=extracTypeInfo(findDeclaration2(qn.getQualifier(),nearestNode));
if (stp == null) {
Class<?> tehClass=findClassIfExists(ps,qn.getQualifier().toString());
if (tehClass != null) {
return definedIn3rdPartyClass(ps,new ClassMember(tehClass),qn.getName().toString());
}
log("QN resolve 3rd par, Can't resolve " + qn.getQualifier());
return null;
}
log("QN, SN Local Type " + getNodeAsString(stp));
ASTNode typeDec=findDeclaration2(stp.getName(),nearestNode);
if (typeDec == null) {
log(stp.getName() + " couldn't be found locally..");
Class<?> tehClass=findClassIfExists(ps,stp.getName().toString());
if (tehClass != null) {
return definedIn3rdPartyClass(ps,new ClassMember(tehClass),qn.getName().toString());
}
log("QN resolve 3rd par, Can't resolve " + qn.getQualifier());
return null;
}
return definedIn3rdPartyClass(ps,new ClassMember(ps,typeDec),qn.getName().toString());
}
 else {
scopeParent=resolveExpression3rdParty(ps,nearestNode,qn.getQualifier(),noCompare);
log("QN, ScopeParent " + scopeParent);
return definedIn3rdPartyClass(ps,scopeParent,qn.getName().toString());
}
}
case ASTNode.ARRAY_ACCESS:
ArrayAccess arac=(ArrayAccess)astNode;
return resolveExpression3rdParty(ps,nearestNode,arac.getArray(),noCompare);
default :
log("Unaccounted type " + getNodeAsString(astNode));
break;
}
return null;
}
