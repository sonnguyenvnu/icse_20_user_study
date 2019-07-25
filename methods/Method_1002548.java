public ExampleRequestResponse action(String name,ResourceLevel resourceLevel){
  ActionSchema actionSchema;
switch (resourceLevel) {
case COLLECTION:
    actionSchema=_resourceSchema.getAction(name);
  break;
case ENTITY:
actionSchema=_resourceSchema.getEntityAction(name);
break;
default :
throw new IllegalArgumentException("Unsupported resourceLevel: " + resourceLevel);
}
if (actionSchema == null) {
throw new IllegalArgumentException("No such action for resource: " + name);
}
DynamicRecordMetadata returnsMetadata=_resourceSpec.getActionResponseMetadata(name);
return buildRequestResponse(buildActionRequest(actionSchema,resourceLevel),buildActionResult(actionSchema),buildResourceMethodDescriptorForAction(name,returnsMetadata,resourceLevel));
}
