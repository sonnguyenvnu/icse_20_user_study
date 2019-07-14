@Override protected String printType(CyclicTypeRecorder ctr){
  StringBuilder sb=new StringBuilder();
  sb.append("<").append(name).append(">");
  return sb.toString();
}
