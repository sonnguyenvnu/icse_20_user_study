private void appendAdditionScript(StringBuilder script,String value,String fieldName,Boolean distinct){
  script.append("if (ctx._source[\"").append(fieldName).append("\"] == null) ctx._source[\"").append(fieldName).append("\"] = [];");
  if (distinct) {
    script.append("if (ctx._source[\"").append(fieldName).append("\"].indexOf(").append(value).append(") == -1) ");
  }
  script.append("ctx._source[\"").append(fieldName).append("\"].add(").append(value).append(");");
}
