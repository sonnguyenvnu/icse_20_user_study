public static Node parseNode(Node prenode){
  if (prenode instanceof Block) {
    List<Node> parsed=parseList(((Block)prenode).statements);
    return new Block(parsed,prenode.file,prenode.start,prenode.end,prenode.line,prenode.col);
  }
  if (prenode instanceof Attr) {
    Attr a=(Attr)prenode;
    return new Attr(parseNode(a.value),a.attr,a.file,a.start,a.end,a.line,a.col);
  }
  if (prenode instanceof Tuple) {
    Tuple tuple=((Tuple)prenode);
    List<Node> elements=tuple.elements;
    if (elements.isEmpty()) {
      _.abort(tuple,"syntax error");
    }
    if (delimType(tuple.open,Constants.RECORD_BEGIN)) {
      return new RecordLiteral(parseList(elements),tuple.file,tuple.start,tuple.end,tuple.line,tuple.col);
    }
    if (delimType(tuple.open,Constants.ARRAY_BEGIN)) {
      return new VectorLiteral(parseList(elements),tuple.file,tuple.start,tuple.end,tuple.line,tuple.col);
    }
    Node keyNode=elements.get(0);
    if (keyNode instanceof Name) {
      String keyword=((Name)keyNode).id;
      if (keyword.equals(Constants.SEQ_KEYWORD)) {
        List<Node> statements=parseList(elements.subList(1,elements.size()));
        return new Block(statements,prenode.file,prenode.start,prenode.end,prenode.line,prenode.col);
      }
      if (keyword.equals(Constants.IF_KEYWORD)) {
        if (elements.size() == 4) {
          Node test=parseNode(elements.get(1));
          Node conseq=parseNode(elements.get(2));
          Node alter=parseNode(elements.get(3));
          return new If(test,conseq,alter,prenode.file,prenode.start,prenode.end,prenode.line,prenode.col);
        }
      }
      if (keyword.equals(Constants.DEF_KEYWORD)) {
        if (elements.size() == 3) {
          Node pattern=parseNode(elements.get(1));
          Node value=parseNode(elements.get(2));
          return new Def(pattern,value,prenode.file,prenode.start,prenode.end,prenode.line,prenode.col);
        }
 else {
          _.abort(tuple,"incorrect format of definition");
        }
      }
      if (keyword.equals(Constants.ASSIGN_KEYWORD)) {
        if (elements.size() == 3) {
          Node pattern=parseNode(elements.get(1));
          Node value=parseNode(elements.get(2));
          return new Assign(pattern,value,prenode.file,prenode.start,prenode.end,prenode.line,prenode.col);
        }
 else {
          _.abort(tuple,"incorrect format of definition");
        }
      }
      if (keyword.equals(Constants.DECLARE_KEYWORD)) {
        if (elements.size() < 2) {
          _.abort(tuple,"syntax error in record type definition");
        }
        Scope properties=parseProperties(elements.subList(1,elements.size()));
        return new Declare(properties,prenode.file,prenode.start,prenode.end,prenode.line,prenode.col);
      }
      if (keyword.equals(Constants.FUN_KEYWORD)) {
        if (elements.size() < 3) {
          _.abort(tuple,"syntax error in function definition");
        }
        Node preParams=elements.get(1);
        if (!(preParams instanceof Tuple)) {
          _.abort(preParams,"incorrect format of parameters: " + preParams);
        }
        boolean hasName=false;
        boolean hasTuple=false;
        List<Name> paramNames=new ArrayList<>();
        List<Node> paramTuples=new ArrayList<>();
        for (        Node p : ((Tuple)preParams).elements) {
          if (p instanceof Name) {
            hasName=true;
            paramNames.add((Name)p);
          }
 else           if (p instanceof Tuple) {
            hasTuple=true;
            List<Node> argElements=((Tuple)p).elements;
            if (argElements.size() == 0) {
              _.abort(p,"illegal argument format: " + p);
            }
            if (!(argElements.get(0) instanceof Name)) {
              _.abort(p,"illegal argument name : " + argElements.get(0));
            }
            Name name=(Name)argElements.get(0);
            if (!name.id.equals(Constants.RETURN_ARROW)) {
              paramNames.add(name);
            }
            paramTuples.add(p);
          }
        }
        if (hasName && hasTuple) {
          _.abort(preParams,"parameters must be either all names or all tuples: " + preParams);
          return null;
        }
        Scope properties;
        if (hasTuple) {
          properties=parseProperties(paramTuples);
        }
 else {
          properties=null;
        }
        List<Node> statements=parseList(elements.subList(2,elements.size()));
        int start=statements.get(0).start;
        int end=statements.get(statements.size() - 1).end;
        Node body=new Block(statements,prenode.file,start,end,prenode.line,prenode.col);
        return new Fun(paramNames,properties,body,prenode.file,prenode.start,prenode.end,prenode.line,prenode.col);
      }
      if (keyword.equals(Constants.RECORD_KEYWORD)) {
        if (elements.size() < 2) {
          _.abort(tuple,"syntax error in record type definition");
        }
        Node name=elements.get(1);
        Node maybeParents=elements.get(2);
        List<Name> parents;
        List<Node> fields;
        if (!(name instanceof Name)) {
          _.abort(name,"syntax error in record name: " + name);
          return null;
        }
        if (maybeParents instanceof Tuple && delimType(((Tuple)maybeParents).open,Constants.TUPLE_BEGIN)) {
          List<Node> parentNodes=((Tuple)maybeParents).elements;
          parents=new ArrayList<>();
          for (          Node p : parentNodes) {
            if (!(p instanceof Name)) {
              _.abort(p,"parents can only be names");
            }
            parents.add((Name)p);
          }
          fields=elements.subList(3,elements.size());
        }
 else {
          parents=null;
          fields=elements.subList(2,elements.size());
        }
        Scope properties=parseProperties(fields);
        return new RecordDef((Name)name,parents,properties,prenode.file,prenode.start,prenode.end,prenode.line,prenode.col);
      }
    }
    Node func=parseNode(elements.get(0));
    List<Node> parsedArgs=parseList(elements.subList(1,elements.size()));
    Argument args=new Argument(parsedArgs);
    return new Call(func,args,prenode.file,prenode.start,prenode.end,prenode.line,prenode.col);
  }
  return prenode;
}
