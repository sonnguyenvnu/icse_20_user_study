@Bean public DictSupportApi dictSupportApi(DictDefineRepository repository){
  return new DefaultDictSupportApi(repository);
}
