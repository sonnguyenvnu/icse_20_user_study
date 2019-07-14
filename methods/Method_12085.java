@Bean public Binding headerBinding(){
  Map<String,Object> map=new HashMap<String,Object>();
  map.put("header1","value1");
  map.put("header2","value2");
  return BindingBuilder.bind(headerQueue1()).to(headersExchage()).whereAll(map).match();
}
