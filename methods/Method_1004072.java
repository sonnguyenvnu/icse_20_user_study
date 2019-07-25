public void filter(final MethodNode methodNode,final IFilterContext context,final IFilterOutput output){
  if (!KotlinGeneratedFilter.isKotlinClass(context)) {
    return;
  }
  new Matcher().match(methodNode,output);
}
