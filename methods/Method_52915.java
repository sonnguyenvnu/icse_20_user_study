private String[] formatNameVariations(final String param){
  final String actualName=param.substring(1);
  return new String[]{param,"${" + actualName + "}","${" + actualName + ".","$!" + actualName,"$!{" + actualName + ".","$!{" + actualName + "}"};
}
