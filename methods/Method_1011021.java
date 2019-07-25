public static void visibility(SNode v,final TextGenContext ctx){
  final TextGenSupport tgs=new TextGenSupport(ctx);
  if ((v == null)) {
    tgs.append("/*package*/ ");
  }
 else {
    tgs.appendNode(v);
  }
}
