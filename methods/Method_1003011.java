/** 
 * Parse the syntax and let the rule call the visitor.
 * @param visitor the visitor
 * @param s the syntax to parse
 */
public void visit(BnfVisitor visitor,String s){
  this.syntax=s;
  tokens=tokenize();
  index=0;
  Rule rule=parseRule();
  rule.setLinks(ruleMap);
  rule.accept(visitor);
}
