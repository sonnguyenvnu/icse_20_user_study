/** 
 * ????
 * @return
 */
private List<Parameter> parameter(){
  List<Parameter> params=new ArrayList<>();
  params.add(new ParameterBuilder().name("Authorization").description("Authorization Bearer token").modelRef(new ModelRef("string")).parameterType("header").required(false).build());
  return params;
}
