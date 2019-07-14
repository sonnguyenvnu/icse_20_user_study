private TypedArray getTypedArray(@AttrRes int attrId){
  int[] attrs=new int[]{attrId};
  if (fixedTheme != null)   return context.getTheme().obtainStyledAttributes(fixedTheme,attrs);
  return context.obtainStyledAttributes(attrs);
}
