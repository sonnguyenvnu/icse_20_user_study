@Override public Pojo duplicate(){
  ElFieldMacro re=new ElFieldMacro();
  re.bin=bin;
  re.entityField=entityField;
  return re;
}
