public static void RectFIntegral(RectF rect){
  rect.left=(int)Math.floor(rect.left);
  rect.top=(int)Math.floor(rect.top);
  rect.right=(int)Math.ceil(rect.right);
  rect.bottom=(int)Math.ceil(rect.bottom);
}
