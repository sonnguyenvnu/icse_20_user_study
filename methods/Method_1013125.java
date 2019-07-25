/** 
 * This computes the decompose field of a NodeRepresentation.
 * @param sn
 * @param decomp The Decomposition from which the initial identifier renamings of the result are obtained.
 * @return
 */
private Decomposition decompose(NodeRepresentation nodeRep,Decomposition decomp,boolean isAssumption){
  SemanticNode sn=nodeRep.semanticNode;
  if (!(sn instanceof OpApplNode)) {
    return null;
  }
  OpApplNode node=(OpApplNode)sn;
  OpApplNode unprimedNode=node;
  Decomposition result=new Decomposition();
  if (decomp != null) {
    result.renaming.identifiers=(Vector<FormalParamNode>)decomp.renaming.identifiers.clone();
    result.renaming.newNames=(Vector<String>)decomp.renaming.newNames.clone();
  }
  result.instantiationSubstitutions=nodeRep.instantiationSubstitutions;
  if (node.getOperator().getName() == ASTConstants.OP_prime) {
    if (!(node.getArgs()[0] instanceof OpApplNode)) {
      return null;
    }
    node=(OpApplNode)node.getArgs()[0];
    unprimedNode=node;
    result.primed=true;
  }
  if ((!nodeRep.isSubexpressionName) && (node.getOperator().getKind() == ASTConstants.UserDefinedOpKind)) {
    OpDefNode definition=(OpDefNode)node.getOperator();
    String operatorName=definition.getName().toString();
    ExprNode opDef=definition.getBody();
    InstanceSubstitution instSubs=nodeRep.instantiationSubstitutions.clone();
    while (opDef instanceof SubstInNode) {
      instSubs=substInNodeToInstanceSub(instSubs,operatorName,(SubstInNode)opDef);
      if (instSubs == null) {
        MessageDialog.openError(UIHelper.getShellProvider().getShell(),"Decompose Proof Command","Decomposing an instantiated definition whose\n" + "instantiation cannot be handled.");
        return null;
      }
      opDef=((SubstInNode)opDef).getBody();
    }
    String instNamePrefix=nodeRep.instantiationSubstitutions.prefix;
    String restOfName=operatorName;
    while (restOfName.indexOf("!") != -1) {
      instNamePrefix=instNamePrefix + restOfName.substring(0,restOfName.indexOf("!") + 1);
      restOfName=restOfName.substring(restOfName.indexOf("!") + 1);
    }
    instSubs.prefix=instNamePrefix;
    if (opDef instanceof OpApplNode) {
      ExprOrOpArgNode[] args=node.getArgs();
      for (int i=0; i < args.length; i++) {
        SyntaxTreeNode stn=(SyntaxTreeNode)args[i].stn;
        Location stnLoc=stn.getLocation();
        if (stnLoc.beginLine() != stnLoc.endLine()) {
          return null;
        }
      }
      node=(OpApplNode)opDef;
      result.moduleName=((SyntaxTreeNode)node.stn).getLocation().source();
      result.instantiationSubstitutions=instSubs;
      result.definedOp=operatorName;
      result.definedOpRep=nodeRep.subNodeText(unprimedNode);
      result.formalParams=definition.getParams();
      result.arguments=new String[result.formalParams.length];
      result.argNodes=unprimedNode.getArgs();
      for (int i=0; i < result.arguments.length; i++) {
        result.arguments[i]=stringArrayToString(nodeRep.subNodeText(((OpApplNode)unprimedNode).getArgs()[i]).nodeText);
      }
    }
 else {
      return null;
    }
  }
  boolean isAndOrOr=false;
  boolean isJunction=false;
  boolean isQuantifier=false;
  boolean isBoundedQuantifier=false;
  if (!(node.getOperator() instanceof OpDefNode)) {
    return null;
  }
  UniqueString opId=((OpDefNode)node.getOperator()).getName();
  String opName=opId.toString();
  if (((opId == ASTConstants.OP_cl) || opName.equals("\\land")) && ((!isAssumption) || conjIsDecomposable(node))) {
    result.type=NodeRepresentation.AND_TYPE;
    if (opId == ASTConstants.OP_cl) {
      isJunction=true;
    }
 else {
      isAndOrOr=true;
    }
  }
 else   if ((opId == ASTConstants.OP_dl) || opName.equals("\\lor")) {
    result.type=NodeRepresentation.OR_TYPE;
    if (opId == ASTConstants.OP_dl) {
      isJunction=true;
    }
 else {
      isAndOrOr=true;
    }
  }
 else   if (opName.equals("=>")) {
    result.type=NodeRepresentation.IMPLIES_TYPE;
  }
 else   if ((opId == ASTConstants.OP_bf) || (opId == ASTConstants.OP_uf)) {
    result.type=NodeRepresentation.FORALL_TYPE;
    isQuantifier=true;
    if (opId == ASTConstants.OP_bf) {
      isBoundedQuantifier=true;
    }
  }
 else   if ((opId == ASTConstants.OP_be) || (opId == ASTConstants.OP_ue)) {
    result.type=NodeRepresentation.EXISTS_TYPE;
    isQuantifier=true;
    if (opId == ASTConstants.OP_be) {
      isBoundedQuantifier=true;
    }
  }
 else   if (opId == ASTConstants.OP_sa) {
    result.type=NodeRepresentation.SQSUB_TYPE;
  }
 else {
    return null;
  }
  if (isAndOrOr) {
    processAndOrOr(result,node,"",opName);
  }
 else   if (isJunction) {
    SemanticNode[] juncts=node.getArgs();
    for (int i=0; i < juncts.length; i++) {
      result.children.add(juncts[i]);
      result.namePath.add("!" + (i + 1));
    }
  }
 else   if (isQuantifier) {
    result.children.add(node.getArgs()[0]);
    String namePath="!(";
    result.quantIds=new Vector<FormalParamNode>();
    if (isBoundedQuantifier) {
      result.quantBounds=new Vector<ExprNode>();
      result.quantBoundsubexpNames=new Vector<String>();
      FormalParamNode[][] quantIdsArray=node.getBdedQuantSymbolLists();
      ExprNode[] quantBounds=node.getBdedQuantBounds();
      for (int i=0; i < quantIdsArray.length; i++) {
        if (node.isBdedQuantATuple()[i]) {
          return null;
        }
        FormalParamNode[] quantIds=quantIdsArray[i];
        for (int j=0; j < quantIds.length; j++) {
          result.quantIds.add(quantIds[j]);
          result.quantBounds.add(quantBounds[i]);
          result.quantBoundsubexpNames.add("!" + (i + 1));
          if (!((i == 0) && (j == 0))) {
            namePath=namePath + ",";
          }
          namePath=namePath + quantIds[j].getName().toString();
        }
      }
    }
 else {
      FormalParamNode[] quantIds=node.getUnbdedQuantSymbols();
      for (int i=0; i < quantIds.length; i++) {
        result.quantIds.add(quantIds[i]);
        if (i != 0) {
          namePath=namePath + ",";
        }
        namePath=namePath + quantIds[i].getName().toString();
      }
    }
    namePath=namePath + ")";
    result.namePath.add(namePath);
  }
 else   if ((result.type == NodeRepresentation.IMPLIES_TYPE) || (result.type == NodeRepresentation.SQSUB_TYPE)) {
    result.children.add(node.getArgs()[0]);
    result.namePath.add("!1");
    result.children.add(node.getArgs()[1]);
    result.namePath.add("!2");
  }
  return result;
}
