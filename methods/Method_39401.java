/** 
 * Builds default field references.
 */
public BeanReferences buildDefaultReference(final PropertyDescriptor propertyDescriptor){
  final PetiteReferenceType[] lookupReferences=petiteConfig.getLookupReferences();
  final String[] references=new String[lookupReferences.length];
  for (int i=0; i < references.length; i++) {
switch (lookupReferences[i]) {
case NAME:
      references[i]=propertyDescriptor.getName();
    break;
case TYPE_SHORT_NAME:
  references[i]=StringUtil.uncapitalize(propertyDescriptor.getType().getSimpleName());
break;
case TYPE_FULL_NAME:
references[i]=propertyDescriptor.getType().getName();
break;
}
}
return BeanReferences.of(references);
}
