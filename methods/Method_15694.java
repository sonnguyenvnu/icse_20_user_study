@Override public Object postProcessAfterInitialization(Object bean,String beanName) throws BeansException {
  if (bean instanceof TwoFactorValidatorProvider) {
    TwoFactorValidatorProvider provider=((TwoFactorValidatorProvider)bean);
    providers.put(provider.getProvider(),provider);
    if (provider.getProvider().equalsIgnoreCase(defaultProvider)) {
      providers.put("default",provider);
    }
  }
  return bean;
}
