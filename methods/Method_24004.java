protected static String[] preprocessFragmentSource(String[] fragSrc0,int version,String versionSuffix){
  if (containsVersionDirective(fragSrc0)) {
    return fragSrc0;
  }
  String[] fragSrc;
  if (version < 130) {
    Pattern[] search={};
    String[] replace={};
    int offset=1;
    fragSrc=preprocessShaderSource(fragSrc0,search,replace,offset);
    fragSrc[0]="#version " + version + versionSuffix;
  }
 else {
    Pattern[] search=new Pattern[]{Pattern.compile(String.format(GLSL_ID_REGEX,"varying|attribute")),Pattern.compile(String.format(GLSL_ID_REGEX,"texture")),Pattern.compile(String.format(GLSL_FN_REGEX,"texture2DRect|texture2D|texture3D|textureCube")),Pattern.compile(String.format(GLSL_ID_REGEX,"gl_FragColor"))};
    String[] replace=new String[]{"in","texMap","texture","_fragColor"};
    int offset=2;
    fragSrc=preprocessShaderSource(fragSrc0,search,replace,offset);
    fragSrc[0]="#version " + version + versionSuffix;
    if (" es".equals(versionSuffix)) {
      fragSrc[1]="out mediump vec4 _fragColor;";
    }
 else {
      fragSrc[1]="out vec4 _fragColor;";
    }
  }
  return fragSrc;
}
