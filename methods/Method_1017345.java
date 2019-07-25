@Override public void invoke(@NotNull final Project project,final Editor editor,@NotNull PsiElement psiElement) throws IncorrectOperationException {
  final XmlTag xmlTag=XmlServiceArgumentIntention.getServiceTagValid(psiElement);
  if (xmlTag == null) {
    return;
  }
  final PhpClass phpClassFromXmlTag=ServiceActionUtil.getPhpClassFromXmlTag(xmlTag,new ContainerCollectionResolver.LazyServiceCollector(project));
  if (phpClassFromXmlTag == null) {
    return;
  }
  Set<String> phpServiceTags=ServiceUtil.getPhpClassServiceTags(phpClassFromXmlTag);
  if (phpServiceTags.size() == 0) {
    HintManager.getInstance().showErrorHint(editor,"Ops, no possible Tag found");
    return;
  }
  for (  XmlTag tag : xmlTag.getSubTags()) {
    if (!"tag".equals(tag.getName())) {
      continue;
    }
    XmlAttribute name=tag.getAttribute("name");
    if (name == null) {
      continue;
    }
    String value=name.getValue();
    if (phpServiceTags.contains(value)) {
      phpServiceTags.remove(value);
    }
  }
  ServiceUtil.insertTagWithPopupDecision(editor,phpServiceTags,tag -> {
    ServiceTag serviceTag=new ServiceTag(phpClassFromXmlTag,tag);
    ServiceUtil.decorateServiceTag(serviceTag);
    xmlTag.addSubTag(XmlElementFactory.getInstance(project).createTagFromText(serviceTag.toXmlString()),false);
  }
);
}
