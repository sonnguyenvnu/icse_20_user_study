@Override public String toString(){
  return consumerConfig != null ? ConfigUniqueNameGenerator.getServiceName(consumerConfig) : super.toString();
}
