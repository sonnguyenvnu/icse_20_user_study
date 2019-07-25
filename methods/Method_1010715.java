protected TransformationMenuItem convert(SubstituteMenuItem item,SubstituteMenuContext context){
  return new DefaultSubstituteMenuItemAsActionItem(item,context);
}
