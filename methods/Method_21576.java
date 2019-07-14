public String parse(boolean isReturn) throws SqlParseException {
  List<String> result=new ArrayList<>();
  String dataType=castExpr.getDataType().getName().toUpperCase();
  String fileName=String.format("doc['%s'].value",Util.expr2Object(castExpr.getExpr()));
  try {
    if (DataType.valueOf(dataType) == DataType.INT) {
      result.add(String.format("def %s = Double.parseDouble(%s.toString()).intValue()",name,fileName));
    }
 else     if (DataType.valueOf(dataType) == DataType.LONG) {
      result.add(String.format("def %s = Double.parseDouble(%s.toString()).longValue()",name,fileName));
    }
 else     if (DataType.valueOf(dataType) == DataType.FLOAT) {
      result.add(String.format("def %s = Double.parseDouble(%s.toString()).floatValue()",name,fileName));
    }
 else     if (DataType.valueOf(dataType) == DataType.DOUBLE) {
      result.add(String.format("def %s = Double.parseDouble(%s.toString()).doubleValue()",name,fileName));
    }
 else     if (DataType.valueOf(dataType) == DataType.STRING) {
      result.add(String.format("def %s = %s.toString()",name,fileName));
    }
 else     if (DataType.valueOf(dataType) == DataType.DATETIME) {
      result.add(String.format("def %s = new Date(Double.parseDouble(%s.toString()).longValue())",name,fileName));
    }
 else {
      throw new SqlParseException("not support cast to data type:" + dataType);
    }
    if (isReturn) {
      result.add("return " + name);
    }
    return Joiner.on("; ").join(result);
  }
 catch (  Exception ex) {
    throw new SqlParseException(String.format("field cast to type: %s failed. error:%s",dataType,ex.getMessage()));
  }
}
