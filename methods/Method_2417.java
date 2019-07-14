@Override public UpmsSystem selectUpmsSystemByName(String name){
  UpmsSystemExample upmsSystemExample=new UpmsSystemExample();
  upmsSystemExample.createCriteria().andNameEqualTo(name);
  List<UpmsSystem> upmsSystems=upmsSystemMapper.selectByExample(upmsSystemExample);
  if (null != upmsSystems && upmsSystems.size() > 0) {
    return upmsSystems.get(0);
  }
  return null;
}
