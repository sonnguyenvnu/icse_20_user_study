public String toJSONString(){
  String stateVal=this.isSuccess() ? AppInfo.getStateInfo(AppInfo.SUCCESS) : this.info;
  StringBuilder builder=new StringBuilder();
  builder.append("{\"state\": \"").append(stateVal).append("\"");
  this.intMap.forEach((k,v) -> builder.append(",\"").append(k).append("\": ").append(v));
  this.infoMap.forEach((k,v) -> builder.append(",\"").append(k).append("\": \"").append(v).append("\""));
  builder.append(", list: [").append(String.join(",",this.stateList.toArray(new String[this.stateList.size()])));
  builder.append(" ]}");
  return Encoder.toUnicode(builder.toString());
}
