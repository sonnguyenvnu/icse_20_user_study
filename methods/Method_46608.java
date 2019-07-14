private DTXLocalControl loadDTXLocalControl(String beanName){
  return spring.getBean(beanName,DTXLocalControl.class);
}
