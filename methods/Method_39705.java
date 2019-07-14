@Override public void visitEnum(final String name,final String desc,final String value){
  elements.put(name,new String[]{desc,value});
}
