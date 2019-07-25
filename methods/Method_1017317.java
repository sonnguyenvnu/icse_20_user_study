@Nullable public LineMarkerInfo collect(PsiElement psiElement){
  if (!Symfony2ProjectComponent.isEnabled(psiElement) || psiElement.getNode().getElementType() != PhpTokenTypes.IDENTIFIER) {
    return null;
  }
  PsiElement method=psiElement.getParent();
  if (!(method instanceof Method) || !((Method)method).getAccess().isPublic()) {
    return null;
  }
  List<GotoRelatedItem> gotoRelatedItems=getGotoRelatedItems((Method)method);
  if (gotoRelatedItems.size() == 0) {
    return null;
  }
  if (gotoRelatedItems.size() == 1) {
    GotoRelatedItem gotoRelatedItem=gotoRelatedItems.get(0);
    Icon icon=null;
    if (gotoRelatedItem instanceof RelatedPopupGotoLineMarker.PopupGotoRelatedItem) {
      icon=((RelatedPopupGotoLineMarker.PopupGotoRelatedItem)gotoRelatedItem).getSmallIcon();
    }
    if (icon == null) {
      icon=Symfony2Icons.SYMFONY_LINE_MARKER;
    }
    NavigationGutterIconBuilder<PsiElement> builder=NavigationGutterIconBuilder.create(icon).setTargets(gotoRelatedItems.get(0).getElement());
    String customName=gotoRelatedItems.get(0).getCustomName();
    if (customName != null) {
      builder.setTooltipText(customName);
    }
    return builder.createLineMarkerInfo(psiElement);
  }
  return new LineMarkerInfo<>(psiElement,psiElement.getTextRange(),Symfony2Icons.SYMFONY_LINE_MARKER,6,new ConstantFunction<>("Related Files"),new RelatedPopupGotoLineMarker.NavigationHandler(gotoRelatedItems),GutterIconRenderer.Alignment.RIGHT);
}
