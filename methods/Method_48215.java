public static String toString(ConfigElement element){
  String result=element.getName();
  if (element.isNamespace()) {
    result="+ " + result;
    if (((ConfigNamespace)element).isUmbrella())     result+=" [*]";
  }
 else {
    result="- " + result;
    ConfigOption option=(ConfigOption)element;
    result+=" [";
switch (option.getType()) {
case FIXED:
      result+="f";
    break;
case GLOBAL_OFFLINE:
  result+="g!";
break;
case GLOBAL:
result+="g";
break;
case MASKABLE:
result+="m";
break;
case LOCAL:
result+="l";
break;
}
result+="," + option.getDatatype().getSimpleName();
result+="," + option.getDefaultValue();
result+="]";
}
result=result + "\n";
String desc=element.getDescription();
result+="\t" + '"' + desc.substring(0,Math.min(desc.length(),50)) + '"';
return result;
}
