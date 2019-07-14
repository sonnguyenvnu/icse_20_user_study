static public boolean isStorable(Object v){
  return v == null || v instanceof Number || v instanceof String || v instanceof Boolean || v instanceof OffsetDateTime || v instanceof EvalError;
}
