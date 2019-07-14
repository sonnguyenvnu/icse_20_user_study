@Override public void afterPropertiesSet() throws Exception {
  sofaRuntimeContext=applicationContext.getBean(SofaRuntimeFrameworkConstants.SOFA_RUNTIME_CONTEXT_BEAN_ID,SofaRuntimeContext.class);
}
