public void execute(SNode node){
  SPropertyOperations.assign(((PropertyReference)FixNamingPolicy_property_once_QuickFix.this.getField("property")[0]).getNode(),((PropertyReference)FixNamingPolicy_property_once_QuickFix.this.getField("property")[0]).getProperty(),NameUtil.captionWithNamingPolicy(SPropertyOperations.getString(((PropertyReference)FixNamingPolicy_property_once_QuickFix.this.getField("property")[0]).getNode(),((PropertyReference)FixNamingPolicy_property_once_QuickFix.this.getField("property")[0]).getProperty())));
}
