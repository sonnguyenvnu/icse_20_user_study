static public Serializable wrapStorable(Object v){
  if (v instanceof ArrayNode) {
    return ((ArrayNode)v).toString();
  }
 else   if (v instanceof ObjectNode) {
    return ((ObjectNode)v).toString();
  }
 else {
    return isStorable(v) ? (Serializable)v : new EvalError(v.getClass().getSimpleName() + " value not storable");
  }
}
