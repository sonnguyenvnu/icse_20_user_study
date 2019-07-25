@Override @Transactional(rollbackFor=Exception.class) public void update(Dict resources){
  Optional<Dict> optionalDict=dictRepository.findById(resources.getId());
  ValidationUtil.isNull(optionalDict,"Dict","id",resources.getId());
  Dict dict=optionalDict.get();
  resources.setId(dict.getId());
  dictRepository.save(resources);
}
