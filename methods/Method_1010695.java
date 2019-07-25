public void customize(String pattern,EditorMenuItemStyle style){
  Optional<EditorMenuItemCompositeCustomizationContext> customizationContext=createCustomizationContext(pattern);
  if (customizationContext.isPresent() && mySourceNode.getModel() != null) {
    EditorMenuItemCustomizationContext finalContext=new EditorMenuItemCompositeCustomizationContext(customizationContext.get(),new CompletionMenuItemCustomizationContext(createCompletionItemInformation(pattern,getOutputSConcept())));
    CompletionItemCustomizationUtil.customize(finalContext,style,mySourceNode.getModel().getRepository());
  }
}
