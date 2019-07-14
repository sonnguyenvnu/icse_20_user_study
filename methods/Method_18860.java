private static boolean isParamOfType(SpecModel specModel,OptionalParameterType optionalParameterType,MethodParamModel methodParamModel){
switch (optionalParameterType) {
case PROP:
    return methodParamModel instanceof PropModel;
case DIFF_PROP:
  return methodParamModel instanceof DiffPropModel;
case TREE_PROP:
return MethodParamModelUtils.isAnnotatedWith(methodParamModel,TreeProp.class);
case STATE:
return methodParamModel instanceof StateParamModel;
case DIFF_STATE:
return methodParamModel instanceof DiffStateParamModel;
case PARAM:
return MethodParamModelUtils.isAnnotatedWith(methodParamModel,Param.class);
case INJECT_PROP:
return MethodParamModelUtils.isAnnotatedWith(methodParamModel,InjectProp.class);
case INTER_STAGE_OUTPUT:
return methodParamModel.getTypeName() instanceof ParameterizedTypeName && ((ParameterizedTypeName)methodParamModel.getTypeName()).rawType.equals(ClassNames.OUTPUT);
case PROP_OUTPUT:
return SpecModelUtils.isPropOutput(specModel,methodParamModel);
case STATE_OUTPUT:
return SpecModelUtils.isStateOutput(specModel,methodParamModel);
case STATE_VALUE:
return SpecModelUtils.isStateValue(specModel,methodParamModel);
case DIFF:
return methodParamModel instanceof RenderDataDiffModel;
case CACHED_VALUE:
return methodParamModel instanceof CachedValueParamModel;
}
return false;
}
