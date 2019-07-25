@Override public void invoke(Matcher m,StringBuilder sb,TemplateOptions templateOptions,String genericCast,ArrayList<String> params){
  expectArgumentCount(m,params,1);
  genericCast=inferTemplateCastName(m,templateOptions,genericCast);
  Type type=inferTemplateType(m,templateOptions,genericCast);
switch (type) {
case GENERIC:
    sb.append(format("((%s[]) new Object [%s])",genericCast,params.get(0)));
  break;
case BYTE:
case CHAR:
case DOUBLE:
case FLOAT:
case INT:
case LONG:
case SHORT:
sb.append(format("(new %s [%s])",type.getType(),params.get(0)));
break;
default :
throw unreachable();
}
}
