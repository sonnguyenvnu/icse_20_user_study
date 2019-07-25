@Override public AlipayConfig find(){
  Optional<AlipayConfig> alipayConfig=alipayRepository.findById(1L);
  if (alipayConfig.isPresent()) {
    return alipayConfig.get();
  }
 else {
    return new AlipayConfig();
  }
}
