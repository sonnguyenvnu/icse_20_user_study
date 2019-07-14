public static long parseFilesize(String in){
  String cleanedInput=in.trim().replaceAll(",",".");
  final Matcher m=Pattern.compile("^([\\d.]+)\\s*(\\w)?b?$",Pattern.CASE_INSENSITIVE).matcher(cleanedInput);
  if (!m.find()) {
    throw new IllegalArgumentException("Invalid size string: \"" + in + "\"");
  }
  int scale=1;
  if (m.group(2) != null) {
switch (m.group(2).toUpperCase()) {
case "G":
      scale*=1024;
case "M":
    scale*=1024;
case "K":
  scale*=1024;
break;
default :
throw new IllegalArgumentException("Invalid size unit: " + m.group(2));
}
}
return Math.round(Double.parseDouble(m.group(1)) * scale);
}
