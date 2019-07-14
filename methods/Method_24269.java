protected void setUniformImpl(String name,int type,Object value){
  if (uniformValues == null) {
    uniformValues=new HashMap<String,UniformValue>();
  }
  uniformValues.put(name,new UniformValue(type,value));
}
