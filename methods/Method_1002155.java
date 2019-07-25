@Override @Transactional(rollbackFor=Exception.class) public void update(DictDetail resources){
  Optional<DictDetail> optionalDictDetail=dictDetailRepository.findById(resources.getId());
  ValidationUtil.isNull(optionalDictDetail,"DictDetail","id",resources.getId());
  DictDetail dictDetail=optionalDictDetail.get();
  resources.setId(dictDetail.getId());
  dictDetailRepository.save(resources);
}
