private void addViewBinding(MethodSpec.Builder result,ViewBinding binding,boolean debuggable){
  if (binding.isSingleFieldBinding()) {
    FieldViewBinding fieldBinding=requireNonNull(binding.getFieldBinding());
    CodeBlock.Builder builder=CodeBlock.builder().add("target.$L = ",fieldBinding.getName());
    boolean requiresCast=requiresCast(fieldBinding.getType());
    if (!debuggable || (!requiresCast && !fieldBinding.isRequired())) {
      if (requiresCast) {
        builder.add("($T) ",fieldBinding.getType());
      }
      builder.add("source.findViewById($L)",binding.getId().code);
    }
 else {
      builder.add("$T.find",UTILS);
      builder.add(fieldBinding.isRequired() ? "RequiredView" : "OptionalView");
      if (requiresCast) {
        builder.add("AsType");
      }
      builder.add("(source, $L",binding.getId().code);
      if (fieldBinding.isRequired() || requiresCast) {
        builder.add(", $S",asHumanDescription(singletonList(fieldBinding)));
      }
      if (requiresCast) {
        builder.add(", $T.class",fieldBinding.getRawType());
      }
      builder.add(")");
    }
    result.addStatement("$L",builder.build());
    return;
  }
  List<MemberViewBinding> requiredBindings=binding.getRequiredBindings();
  if (!debuggable || requiredBindings.isEmpty()) {
    result.addStatement("view = source.findViewById($L)",binding.getId().code);
  }
 else   if (!binding.isBoundToRoot()) {
    result.addStatement("view = $T.findRequiredView(source, $L, $S)",UTILS,binding.getId().code,asHumanDescription(requiredBindings));
  }
  addFieldBinding(result,binding,debuggable);
  addMethodBindings(result,binding,debuggable);
}
