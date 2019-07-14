String getExifInfo(){
  StringBuilder result=new StringBuilder();
  String asd;
  if ((asd=getfNumber()) != null)   result.append(asd).append(" ");
  if ((asd=getExposureTime()) != null)   result.append(asd).append(" ");
  if ((asd=getIso()) != null)   result.append(asd).append(" ");
  return result.length() == 0 ? null : result.toString();
}
