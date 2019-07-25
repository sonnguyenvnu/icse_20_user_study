/** 
 * Generates the translation.
 * @param ast  The AST produced by parsing and exploding.
 * @param symtab The symbol table.
 * @param report  A vector of strings, containing the reports of renaming.
 * @return A vector of strings.
 * @throws PcalTLAGenException
 */
public Vector<String> generate(AST ast,PcalSymTab symtab,Vector report) throws PcalTLAGenException {
  TLAtoPCalMapping map=PcalParams.tlaPcalMapping;
  mappingVector=new Vector<String>(50);
  for (int i=0; i < report.size(); i++) {
    addOneLineOfTLA((String)report.elementAt(i));
  }
  st=symtab;
  GenSym(ast,"");
  PCalLocation ZeroLocation=new PCalLocation(0,0);
  ((Vector)mappingVector.elementAt(0)).add(0,new MappingObject.LeftParen(ZeroLocation));
  Vector lastLine=(Vector)mappingVector.elementAt(mappingVector.size() - 1);
  lastLine.add(lastLine.size(),new MappingObject.RightParen(ZeroLocation));
  int parenDepth=0;
  for (int i=0; i < mappingVector.size(); i++) {
    Vector line=(Vector)mappingVector.elementAt(i);
    for (int j=0; j < line.size(); j++) {
      MappingObject obj=(MappingObject)line.elementAt(j);
      if (obj.getType() == MappingObject.LEFT_PAREN) {
        parenDepth++;
      }
 else       if (obj.getType() == MappingObject.RIGHT_PAREN) {
        parenDepth--;
        if (parenDepth < 0) {
          throw new NullPointerException("paren depth < 0");
        }
      }
    }
  }
  if (parenDepth != 0) {
    throw new NullPointerException("Unmatched Left Paren");
  }
  Vector nonredundantMappingVector=TLAtoPCalMapping.RemoveRedundantParens(mappingVector);
  map.makeMapping(nonredundantMappingVector);
  return tlacode;
}
