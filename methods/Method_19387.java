private static Component createCaret(ComponentContext c,Drawable icon,boolean isShowingDropDown){
  return Image.create(c).drawable(icon).widthDip(SPINNER_HEIGHT).heightDip(SPINNER_HEIGHT).flexShrink(0).flexGrow(0).scale(isShowingDropDown ? -1 : 1).build();
}
