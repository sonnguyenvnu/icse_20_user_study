static protected void writeValue(StringBuffer sb,Object v,boolean quote){
  if (ExpressionUtils.isError(v)) {
    sb.append("[error: " + ((EvalError)v).message + "]");
  }
 else {
    if (v == null) {
      sb.append("null");
    }
 else {
      if (v instanceof WrappedCell) {
        sb.append("[object Cell]");
      }
 else       if (v instanceof WrappedRow) {
        sb.append("[object Row]");
      }
 else       if (v instanceof ObjectNode) {
        sb.append(((ObjectNode)v).toString());
      }
 else       if (v instanceof ArrayNode) {
        sb.append(((ArrayNode)v).toString());
      }
 else       if (ExpressionUtils.isArray(v)) {
        Object[] a=(Object[])v;
        sb.append("[ ");
        for (int i=0; i < a.length; i++) {
          if (i > 0) {
            sb.append(", ");
          }
          writeValue(sb,a[i],true);
        }
        sb.append(" ]");
      }
 else       if (ExpressionUtils.isArrayOrList(v)) {
        List<Object> list=ExpressionUtils.toObjectList(v);
        sb.append("[ ");
        for (int i=0; i < list.size(); i++) {
          if (i > 0) {
            sb.append(", ");
          }
          writeValue(sb,list.get(i),true);
        }
        sb.append(" ]");
      }
 else       if (v instanceof HasFields) {
        sb.append("[object " + v.getClass().getSimpleName() + "]");
      }
 else       if (v instanceof OffsetDateTime) {
        sb.append("[date " + ParsingUtilities.dateToString((OffsetDateTime)v) + "]");
      }
 else       if (v instanceof String) {
        if (quote) {
          try {
            sb.append(ParsingUtilities.mapper.writeValueAsString(((String)v)));
          }
 catch (          JsonProcessingException e) {
          }
        }
 else {
          sb.append((String)v);
        }
      }
 else       if (v instanceof Double || v instanceof Float) {
        Number n=(Number)v;
        if (n.doubleValue() - n.longValue() == 0.0) {
          sb.append(n.longValue());
        }
 else {
          sb.append(n.doubleValue());
        }
      }
 else {
        sb.append(v.toString());
      }
    }
  }
}
