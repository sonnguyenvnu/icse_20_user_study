private String addLineNumbers(String source){
  StringBuilder result=new StringBuilder((int)(source.length() * 1.2));
  int count=1;
  for (  String line : source.split("\n")) {
    result.append("<span class='lineno'>");
    result.append(count++);
    result.append("</span> ");
    result.append(line);
    result.append("\n");
  }
  return result.toString();
}
