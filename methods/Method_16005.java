/** 
 * ?????,????? {@link ExpressionUtils#PATTERN}????<br> ??? analytical("http://${3+2}/test",var,"spel")<br> ????????: <ul> <li>freemarker</li> <li>spel</li> <li>ognl</li> <li>groovy</li> <li>js</li> </ul>
 * @param expression ??????
 * @param vars       ??
 * @param language   ?????
 * @return ????
 * @throws Exception ????
 */
public static String analytical(String expression,Map<String,Object> vars,String language) throws Exception {
  Matcher matcher=PATTERN.matcher(expression);
  DynamicScriptEngine engine=DynamicScriptEngineFactory.getEngine(language);
  if (engine == null) {
    return expression;
  }
  vars=new HashMap<>(vars);
  vars.putAll(getDefaultVar());
  while (matcher.find()) {
    String real_expression=matcher.group();
    String e_id=String.valueOf(real_expression.hashCode());
    if (!engine.compiled(e_id)) {
      engine.compile(e_id,real_expression);
    }
    ExecuteResult result=engine.execute(e_id,vars);
    if (!result.isSuccess()) {
      throw new RuntimeException(result.getMessage(),result.getException());
    }
    String obj=String.valueOf(result.get());
    expression=expression.replace("${" + real_expression + "}",obj);
  }
  return expression;
}
