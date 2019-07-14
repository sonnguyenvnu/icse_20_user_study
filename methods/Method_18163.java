/** 
 * This is a wrapper on top of built in  {@link Drawable#getPadding(Rect)} which overrides defaultreturn value. The reason why we need this - is because on pre-L devices LayerDrawable always returns "true" even if drawable doesn't have padding (see https://goo.gl/gExcMQ). Since we heavily rely on correctness of this information, we need to check padding manually
 */
private static boolean getDrawablePadding(Drawable drawable,Rect outRect){
  drawable.getPadding(outRect);
  return outRect.bottom != 0 || outRect.top != 0 || outRect.left != 0 || outRect.right != 0;
}
