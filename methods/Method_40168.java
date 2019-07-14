private String toCSS(@NotNull Style style){
  return style.type.toString().toLowerCase().replace("_","-");
}
