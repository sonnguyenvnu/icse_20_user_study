static TypeSpecDataHolder generatePropsBuilderMethods(SpecModel specModel,PropModel prop,int requiredIndex){
  final TypeSpecDataHolder.Builder dataHolder=TypeSpecDataHolder.newBuilder();
  final boolean hasVarArgs=prop.hasVarArgs();
switch (prop.getResType()) {
case STRING:
    dataHolder.addTypeSpecDataHolder(regularBuilders(specModel,prop,requiredIndex));
  dataHolder.addTypeSpecDataHolder(resBuilders(specModel,prop,requiredIndex,ClassNames.STRING_RES,"resolveString"));
dataHolder.addTypeSpecDataHolder(resWithVarargsBuilders(specModel,prop,requiredIndex,ClassNames.STRING_RES,"resolveString",TypeName.OBJECT,"formatArgs"));
dataHolder.addTypeSpecDataHolder(attrBuilders(specModel,prop,requiredIndex,ClassNames.STRING_RES,"resolveString"));
break;
case STRING_ARRAY:
dataHolder.addTypeSpecDataHolder(regularBuilders(specModel,prop,requiredIndex));
dataHolder.addTypeSpecDataHolder(resBuilders(specModel,prop,requiredIndex,ClassNames.ARRAY_RES,"resolveStringArray"));
dataHolder.addTypeSpecDataHolder(attrBuilders(specModel,prop,requiredIndex,ClassNames.ARRAY_RES,"resolveStringArray"));
break;
case INT:
dataHolder.addTypeSpecDataHolder(regularBuilders(specModel,prop,requiredIndex));
dataHolder.addTypeSpecDataHolder(resBuilders(specModel,prop,requiredIndex,ClassNames.INT_RES,"resolveInt"));
dataHolder.addTypeSpecDataHolder(attrBuilders(specModel,prop,requiredIndex,ClassNames.INT_RES,"resolveInt"));
break;
case INT_ARRAY:
dataHolder.addTypeSpecDataHolder(regularBuilders(specModel,prop,requiredIndex));
dataHolder.addTypeSpecDataHolder(resBuilders(specModel,prop,requiredIndex,ClassNames.ARRAY_RES,hasVarArgs ? "resolveIntegerArray" : "resolveIntArray"));
dataHolder.addTypeSpecDataHolder(attrBuilders(specModel,prop,requiredIndex,ClassNames.ARRAY_RES,hasVarArgs ? "resolveIntegerArray" : "resolveIntArray"));
break;
case BOOL:
dataHolder.addTypeSpecDataHolder(regularBuilders(specModel,prop,requiredIndex));
dataHolder.addTypeSpecDataHolder(resBuilders(specModel,prop,requiredIndex,ClassNames.BOOL_RES,"resolveBool"));
dataHolder.addTypeSpecDataHolder(attrBuilders(specModel,prop,requiredIndex,ClassNames.BOOL_RES,"resolveBool"));
break;
case COLOR:
dataHolder.addTypeSpecDataHolder(regularBuilders(specModel,prop,requiredIndex,annotation(ClassNames.COLOR_INT)));
dataHolder.addTypeSpecDataHolder(resBuilders(specModel,prop,requiredIndex,ClassNames.COLOR_RES,"resolveColor"));
dataHolder.addTypeSpecDataHolder(attrBuilders(specModel,prop,requiredIndex,ClassNames.COLOR_RES,"resolveColor"));
break;
case DIMEN_SIZE:
dataHolder.addTypeSpecDataHolder(pxBuilders(specModel,prop,requiredIndex));
dataHolder.addTypeSpecDataHolder(dipBuilders(specModel,prop,requiredIndex));
dataHolder.addTypeSpecDataHolder(resBuilders(specModel,prop,requiredIndex,ClassNames.DIMEN_RES,"resolveDimenSize"));
dataHolder.addTypeSpecDataHolder(attrBuilders(specModel,prop,requiredIndex,ClassNames.DIMEN_RES,"resolveDimenSize"));
break;
case DIMEN_TEXT:
dataHolder.addTypeSpecDataHolder(pxBuilders(specModel,prop,requiredIndex));
dataHolder.addTypeSpecDataHolder(dipBuilders(specModel,prop,requiredIndex));
dataHolder.addTypeSpecDataHolder(sipBuilders(specModel,prop,requiredIndex));
dataHolder.addTypeSpecDataHolder(resBuilders(specModel,prop,requiredIndex,ClassNames.DIMEN_RES,"resolveDimenSize"));
dataHolder.addTypeSpecDataHolder(attrBuilders(specModel,prop,requiredIndex,ClassNames.DIMEN_RES,"resolveDimenSize"));
break;
case DIMEN_OFFSET:
dataHolder.addTypeSpecDataHolder(pxBuilders(specModel,prop,requiredIndex));
dataHolder.addTypeSpecDataHolder(dipBuilders(specModel,prop,requiredIndex));
dataHolder.addTypeSpecDataHolder(sipBuilders(specModel,prop,requiredIndex));
dataHolder.addTypeSpecDataHolder(resBuilders(specModel,prop,requiredIndex,ClassNames.DIMEN_RES,"resolveDimenSize"));
dataHolder.addTypeSpecDataHolder(attrBuilders(specModel,prop,requiredIndex,ClassNames.DIMEN_RES,"resolveDimenSize"));
break;
case FLOAT:
dataHolder.addTypeSpecDataHolder(regularBuilders(specModel,prop,requiredIndex));
dataHolder.addTypeSpecDataHolder(resBuilders(specModel,prop,requiredIndex,ClassNames.DIMEN_RES,"resolveFloat"));
dataHolder.addTypeSpecDataHolder(attrBuilders(specModel,prop,requiredIndex,ClassNames.DIMEN_RES,"resolveFloat"));
break;
case DRAWABLE:
dataHolder.addTypeSpecDataHolder(regularBuilders(specModel,prop,requiredIndex));
dataHolder.addTypeSpecDataHolder(resBuilders(specModel,prop,requiredIndex,ClassNames.DRAWABLE_RES,"resolveDrawable"));
dataHolder.addTypeSpecDataHolder(attrBuilders(specModel,prop,requiredIndex,ClassNames.DRAWABLE_RES,"resolveDrawable"));
break;
case NONE:
if (hasVarArgs) {
dataHolder.addMethod(varArgBuilder(specModel,prop,requiredIndex));
ParameterizedTypeName type=(ParameterizedTypeName)prop.getTypeName();
if (getRawType(type.typeArguments.get(0)).equals(ClassNames.COMPONENT)) {
dataHolder.addMethod(varArgBuilderBuilder(specModel,prop,requiredIndex));
}
}
final TypeName componentClass=prop.getTypeName() instanceof ParameterizedTypeName ? ((ParameterizedTypeName)prop.getTypeName()).rawType : prop.getTypeName();
if (componentClass.equals(ClassNames.COMPONENT)) {
dataHolder.addMethod(componentBuilder(specModel,prop,requiredIndex));
}
 else if (prop.isDynamic()) {
final TypeName dynamicValueType=ParameterizedTypeName.get(ClassNames.DYNAMIC_VALUE,prop.getTypeName().box());
dataHolder.addMethod(dynamicValueBuilder(specModel,prop,requiredIndex,dynamicValueType));
dataHolder.addMethod(dynamicValueSimpleBuilder(specModel,prop,requiredIndex,dynamicValueType));
}
 else {
dataHolder.addMethod(regularBuilder(specModel,prop,requiredIndex));
}
break;
}
if (getRawType(prop.getTypeName()).equals(ClassNames.COMPONENT)) {
dataHolder.addMethod(builderBuilder(specModel,prop,requiredIndex,ClassNames.COMPONENT_BUILDER,true));
}
if (getRawType(prop.getTypeName()).equals(ClassNames.SECTION)) {
dataHolder.addMethod(builderBuilder(specModel,prop,requiredIndex,ClassNames.SECTION_BUILDER,true));
}
return dataHolder.build();
}
