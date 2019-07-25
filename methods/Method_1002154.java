@Override @Transactional(rollbackFor=Exception.class) public DictDetailDTO create(DictDetail resources){
  return dictDetailMapper.toDto(dictDetailRepository.save(resources));
}
