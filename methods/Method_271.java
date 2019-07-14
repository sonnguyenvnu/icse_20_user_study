@Override public CodeBlock render(int sdk){
  CodeBlock typeface=sdk >= 26 ? CodeBlock.of("res.getFont($L)",id.code) : CodeBlock.of("$T.getFont(context, $L)",RESOURCES_COMPAT,id.code);
  if (style != TypefaceStyles.NORMAL) {
    typeface=CodeBlock.of("$1T.create($2L, $1T.$3L)",TYPEFACE,typeface,style);
  }
  return CodeBlock.of("target.$L = $L",name,typeface);
}
