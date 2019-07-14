public static Spanned htmlFormat(String content,int color,boolean bold,boolean italic){
  String res=content;
  if (color != -1)   res=color(color,res);
  if (bold)   res=b(res);
  if (italic)   res=i(res);
  return html(res);
}
