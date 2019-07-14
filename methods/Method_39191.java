@Override public void inject(final Targets targets){
  targets.forEachTarget(target -> {
    final Class targetType=target.resolveType();
    final String baseName=baseNameOf(targetType);
    final ParamManager madvocPetiteParamManager=madpc.paramManager();
    final String[] params=madvocPetiteParamManager.filterParametersForBeanName(baseName,true);
    for (    final String param : params) {
      final Object value=madvocPetiteParamManager.get(param);
      final String propertyName=param.substring(baseName.length() + 1);
      target.writeValue(propertyName,value,false);
    }
  }
);
}
