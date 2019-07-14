public static SocialSecurityNumber valueOf(@MaskFormat("###-##-####") String value){
  return new SocialSecurityNumber(value);
}
