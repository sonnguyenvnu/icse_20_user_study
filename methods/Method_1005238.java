public void execute(Environment env,Map params,TemplateModel[] loopVars,TemplateDirectiveBody body) throws TemplateException, IOException {
  String exp=getAttribute(params,"exp");
  String data=getAttribute(params,"data");
  if (exp == null || data == null) {
    throw new TemplateException("Can not find attribute 'name' in data tag",env);
  }
  StringBuilder resexp=new StringBuilder();
  String[] ShowbyFields=exp.split("&&");
  for (  String ShowbyField : ShowbyFields) {
    int beginIndex=ShowbyField.indexOf("#");
    int endIndex=ShowbyField.lastIndexOf("#");
    String exptype=ShowbyField.substring(beginIndex + 1,endIndex);
    String field=ShowbyField.substring(0,beginIndex);
    String[] values=ShowbyField.substring(endIndex + 1,ShowbyField.length()).split(",");
    String value="";
    for (int i=0; i < values.length; i++) {
      value+="'" + "" + values[i] + "" + "'";
      if (i < values.length - 1) {
        value+=",";
      }
    }
    if ("eq".equals(exptype)) {
      resexp.append("$.inArray(" + data + "." + field + ",[" + value + "])>=0");
    }
    if ("ne".equals(exptype)) {
      resexp.append("$.inArray(" + data + "." + field + ",[" + value + "])<0");
    }
    if ("empty".equals(exptype) && value.equals("'true'")) {
      resexp.append("" + data + "." + field + "==''");
    }
    if ("empty".equals(exptype) && value.equals("'false'")) {
      resexp.append("" + data + "." + field + "!=''");
    }
  }
  Writer out=env.getOut();
  out.append(resexp);
}
