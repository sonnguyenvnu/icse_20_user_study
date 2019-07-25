@Override public void annotate(PhpAnnotationDocTagAnnotatorParameter parameter){
  if (!Symfony2ProjectComponent.isEnabled(parameter.getProject()) || !PhpElementsUtil.isEqualClassName(parameter.getAnnotationClass(),TwigUtil.TEMPLATE_ANNOTATION_CLASS)) {
    return;
  }
  PhpPsiElement phpDocAttrList=parameter.getPhpDocTag().getFirstPsiChild();
  if (phpDocAttrList == null) {
    return;
  }
  String tagValue=phpDocAttrList.getText();
  Collection<String> templateNames=new HashSet<>();
  Matcher matcher=Pattern.compile("\\(\"(.*)\"").matcher(tagValue);
  if (matcher.find()) {
    templateNames.add(matcher.group(1));
  }
 else {
    PhpDocComment docComment=PsiTreeUtil.getParentOfType(parameter.getPhpDocTag(),PhpDocComment.class);
    if (null == docComment) {
      return;
    }
    Method method=PsiTreeUtil.getNextSiblingOfType(docComment,Method.class);
    if (null == method || (!method.getName().endsWith("Action") && !method.getName().equalsIgnoreCase("__invoke"))) {
      return;
    }
    templateNames.addAll(Arrays.asList(TwigUtil.getControllerMethodShortcut(method)));
  }
  if (templateNames.size() == 0) {
    return;
  }
  for (  String templateName : templateNames) {
    if (TwigUtil.getTemplateFiles(parameter.getProject(),templateName).size() > 0) {
      return;
    }
  }
  String templateName=ContainerUtil.find(templateNames,s -> s.toLowerCase().endsWith(".html.twig"));
  if (templateName == null) {
    templateName=templateNames.iterator().next();
  }
  PsiElement firstChild=parameter.getPhpDocTag().getFirstChild();
  if (null == firstChild) {
    return;
  }
  parameter.getHolder().createWarningAnnotation(firstChild.getTextRange(),"Twig: Missing Template").registerFix(new TemplateCreateByNameLocalQuickFix(templateName));
}
