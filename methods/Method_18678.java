@Override protected void buildDialog(Project project,PsiDirectory directory,CreateFileFromTemplateDialog.Builder builder){
  LithoLoggerProvider.getEventLogger().log(EventLogger.EVENT_NEW_TEMPLATE);
  builder.setTitle(TITLE).addKind("Layout Component",AllIcons.Nodes.AbstractClass,"LayoutSpec").addKind("GroupSection Component",AllIcons.Nodes.AbstractClass,"GroupSectionSpec").addKind("Mount Component",AllIcons.Nodes.AbstractClass,"MountSpec");
}
