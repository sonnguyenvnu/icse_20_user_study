/** 
 * The main function that calculates possible code completion candidates
 * @param pdePhrase
 * @param line
 * @param lineStartNonWSOffset
 */
public List<CompletionCandidate> preparePredictions(final PreprocessedSketch ps,final String pdePhrase,final int lineNumber){
  Messages.log("* preparePredictions");
  ASTNode astRootNode=(ASTNode)ps.compilationUnit.types().get(0);
  TextTransform transform=new TextTransform(pdePhrase);
  transform.addAll(SourceUtils.replaceTypeConstructors(pdePhrase));
  transform.addAll(SourceUtils.replaceHexLiterals(pdePhrase));
  transform.addAll(SourceUtils.replaceColorRegex(pdePhrase));
  transform.addAll(SourceUtils.fixFloatsRegex(pdePhrase));
  String phrase=transform.apply();
  boolean noCompare=phrase.endsWith(".");
  if (noCompare) {
    phrase=phrase.substring(0,phrase.length() - 1);
  }
  boolean incremental=!noCompare && phrase.length() > lastPredictedPhrase.length() && phrase.startsWith(lastPredictedPhrase);
  if (incremental) {
    log(pdePhrase + " starts with " + lastPredictedPhrase);
    log("Don't recalc");
    if (phrase.contains(".")) {
      int x=phrase.lastIndexOf('.');
      candidates=trimCandidates(phrase.substring(x + 1),candidates);
    }
 else {
      candidates=trimCandidates(phrase,candidates);
    }
    lastPredictedPhrase=phrase;
    return candidates;
  }
  ASTNode nearestNode;
  ASTParser parser=ASTParser.newParser(AST.JLS8);
  parser.setKind(ASTParser.K_EXPRESSION);
  parser.setSource(phrase.toCharArray());
  ASTNode testnode=parser.createAST(null);
  Messages.loge("Typed: " + phrase + "|" + " temp Node type: " + testnode.getClass().getSimpleName());
  if (testnode instanceof MethodInvocation) {
    MethodInvocation mi=(MethodInvocation)testnode;
    log(mi.getName() + "," + mi.getExpression() + "," + mi.typeArguments().size());
  }
  nearestNode=findClosestNode(lineNumber,astRootNode);
  if (nearestNode == null) {
    nearestNode=astRootNode;
  }
  Messages.loge(lineNumber + " Nearest ASTNode to PRED " + getNodeAsString(nearestNode));
  candidates=new ArrayList<>();
  lastPredictedPhrase=phrase;
  if (testnode instanceof SimpleName && !noCompare) {
    Messages.loge("One word expression " + getNodeAsString(testnode));
    while (nearestNode != null) {
      if (nearestNode instanceof TypeDeclaration) {
        TypeDeclaration td=(TypeDeclaration)nearestNode;
        if (td.getStructuralProperty(TypeDeclaration.SUPERCLASS_TYPE_PROPERTY) != null) {
          SimpleType st=(SimpleType)td.getStructuralProperty(TypeDeclaration.SUPERCLASS_TYPE_PROPERTY);
          log("Superclass " + st.getName());
          ArrayList<CompletionCandidate> tempCandidates=getMembersForType(ps,st.getName().toString(),phrase,false,false);
          for (          CompletionCandidate can : tempCandidates) {
            candidates.add(can);
          }
        }
      }
      List<StructuralPropertyDescriptor> sprops=nearestNode.structuralPropertiesForType();
      for (      StructuralPropertyDescriptor sprop : sprops) {
        ASTNode cnode;
        if (!sprop.isChildListProperty()) {
          if (nearestNode.getStructuralProperty(sprop) instanceof ASTNode) {
            cnode=(ASTNode)nearestNode.getStructuralProperty(sprop);
            CompletionCandidate[] types=checkForTypes(cnode);
            if (types != null) {
              for (              CompletionCandidate type : types) {
                if (type.getElementName().toLowerCase().startsWith(phrase.toLowerCase()))                 candidates.add(type);
              }
            }
          }
        }
 else {
          List<ASTNode> nodelist=(List<ASTNode>)nearestNode.getStructuralProperty(sprop);
          for (          ASTNode clnode : nodelist) {
            CompletionCandidate[] types=checkForTypes(clnode);
            if (types != null) {
              for (              CompletionCandidate type : types) {
                if (type.getElementName().toLowerCase().startsWith(phrase.toLowerCase()))                 candidates.add(type);
              }
            }
          }
        }
      }
      nearestNode=nearestNode.getParent();
    }
    log("Empty can. " + phrase);
    ClassPath classPath=ps.classPath;
    if (classPath != null) {
      RegExpResourceFilter regExpResourceFilter=new RegExpResourceFilter(Pattern.compile(".*"),Pattern.compile(phrase + "[a-zA-Z_0-9]*.class",Pattern.CASE_INSENSITIVE));
      String[] resources=classPath.findResources("",regExpResourceFilter);
      for (      String matchedClass2 : resources) {
        matchedClass2=matchedClass2.replace('/','.');
        String matchedClass=matchedClass2.substring(0,matchedClass2.length() - 6);
        int d=matchedClass.lastIndexOf('.');
        if (!ignorableSuggestionImport(ps,matchedClass)) {
          matchedClass=matchedClass.substring(d + 1);
          String html="<html>" + matchedClass + " : <font color=#777777>" + matchedClass2.substring(0,d) + "</font></html>";
          candidates.add(new CompletionCandidate(matchedClass,html,matchedClass,CompletionCandidate.PREDEF_CLASS));
        }
      }
    }
  }
 else {
    Messages.loge("Complex expression " + getNodeAsString(testnode));
    log("candidates empty");
    ASTNode childExpr=getChildExpression(testnode);
    log("Parent expression : " + getParentExpression(testnode));
    log("Child expression : " + childExpr);
    if (!noCompare) {
      log("Original testnode " + getNodeAsString(testnode));
      testnode=getParentExpression(testnode);
      log("Corrected testnode " + getNodeAsString(testnode));
    }
    ClassMember expr=resolveExpression3rdParty(ps,nearestNode,testnode,noCompare);
    if (expr == null) {
      log("Expr is null");
    }
 else {
      boolean isArray=expr.thisclass != null && expr.thisclass.isArray();
      boolean isSimpleType=(expr.astNode != null) && expr.astNode.getNodeType() == ASTNode.SIMPLE_TYPE;
      boolean isMethod=expr.method != null;
      boolean staticOnly=!isMethod && !isArray && !isSimpleType;
      log("Expr is " + expr.toString());
      String lookFor=(noCompare || (childExpr == null)) ? "" : childExpr.toString();
      candidates=getMembersForType(ps,expr,lookFor,noCompare,staticOnly);
    }
  }
  return candidates;
}
