public static String getLocaleAlias(String code){
  if (code == null) {
    return null;
  }
switch (code) {
case "in":
    return "id";
case "iw":
  return "he";
case "jw":
return "jv";
case "no":
return "nb";
case "tl":
return "fil";
case "ji":
return "yi";
case "id":
return "in";
case "he":
return "iw";
case "jv":
return "jw";
case "nb":
return "no";
case "fil":
return "tl";
case "yi":
return "ji";
}
return null;
}
