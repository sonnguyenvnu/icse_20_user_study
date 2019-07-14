@Override @Caching(evict={@CacheEvict(key="'md5:'+#target.selectByPk(#id).md5"),@CacheEvict(key="'id:'+#id"),@CacheEvict(key="'id-or-md5:'+#id"),@CacheEvict(key="'id-or-md5:'+#id")}) public FileInfoEntity deleteByPk(String id){
  return super.deleteByPk(id);
}
