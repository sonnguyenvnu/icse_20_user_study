public List<TransformationMenuItem> collect(){
  DefaultSubstituteMenuContext substituteMenuContext=new DefaultSubstituteMenuContextBuilder(myParent,myEditorContext).setContainmentLink(myContainmentLink).setTargetConcept(myTargetConcept).setCurrentChild(myCurrentChild).setEditorMenuTrace(myEditorMenuTrace).setCustomizers(myCustomizers).createDefaultSubstituteMenuContext();
  return substituteMenuContext.createItems(myMenuLookup).stream().map(item -> convert(item,substituteMenuContext)).collect(Collectors.toList());
}
