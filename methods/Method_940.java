@Override public void addViolation(Object data,Node node,String arg){
  String name=getIdentifyName((AbstractVmNode)node);
  String text=I18nResources.getMessage("vm.other.UseQuietReferenceNotationRule.violation.msg",name);
  addViolationWithMessage(data,node,text);
}
