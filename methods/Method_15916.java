@Bean public BeanPostProcessor sqlTermCustomizerRegister(){
  List<Dialect> dialects=Arrays.asList(Dialect.H2,Dialect.MYSQL,Dialect.ORACLE,Dialect.POSTGRES,Dialect.MSSQL);
  return new BeanPostProcessor(){
    @Override public Object postProcessBeforeInitialization(    Object bean,    String beanName) throws BeansException {
      return bean;
    }
    @Override public Object postProcessAfterInitialization(    Object bean,    String beanName) throws BeansException {
      if (bean instanceof SqlTermCustomizer) {
        SqlTermCustomizer customizer=((SqlTermCustomizer)bean);
        if (customizer.forDialect() != null) {
          for (          Dialect dialect : customizer.forDialect()) {
            dialect.setTermTypeMapper(customizer.getTermType(),customizer);
          }
        }
 else {
          dialects.forEach(dialect -> dialect.setTermTypeMapper(customizer.getTermType(),customizer));
        }
      }
      return bean;
    }
  }
;
}
