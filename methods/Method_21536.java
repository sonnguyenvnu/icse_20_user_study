@Override public Object clone() throws CloneNotSupportedException {
  try {
    Condition clonedCondition=new Condition(this.getConn(),this.getName(),this.getNameExpr(),this.getOpear(),this.getValue(),this.getValueExpr(),this.getRelationshipType());
    return clonedCondition;
  }
 catch (  SqlParseException e) {
  }
  return null;
}
