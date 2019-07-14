private String getBadSuffixOrNull(ASTPrimaryExpression exp,int equalsPosition){
  if (!(exp.jjtGetChild(equalsPosition) instanceof ASTPrimarySuffix)) {
    return null;
  }
  ASTPrimarySuffix suffix=(ASTPrimarySuffix)exp.jjtGetChild(equalsPosition);
  if (suffix.getImage() == null || !(suffix.hasImageEqualTo("equals") || suffix.hasImageEqualTo("equalsIgnoreCase"))) {
    return null;
  }
  return suffix.getImage();
}
