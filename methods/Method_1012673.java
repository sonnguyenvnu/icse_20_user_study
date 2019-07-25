public void add(DLNAResource resource){
  DLNAResource res1;
  LOGGER.debug("Adding \"{}\" to playlist \"{}\"",resource.getDisplayName(),getName());
  if (resource instanceof VirtualVideoAction) {
    return;
  }
  if (resource.getParent() == this) {
    res1=resource;
    for (    DLNAResource r : list) {
      if (r.getName().equals(resource.getName()) && r.getSystemName().equals(resource.getSystemName())) {
        res1=r;
        break;
      }
    }
  }
 else {
    String data=resource.write();
    if (!StringUtils.isEmpty(data) && resource.getMasterParent() != null) {
      res1=IOList.resolveCreateMethod(resource.getMasterParent(),data);
      res1.setMasterParent(resource.getMasterParent());
      res1.setMediaAudio(resource.getMediaAudio());
      res1.setMediaSubtitle(resource.getMediaSubtitle());
      res1.setResume(resource.getResume());
    }
 else {
      res1=resource.clone();
      res1.setResume(resource.getResume());
    }
  }
  list.remove(res1);
  if (maxSize > 0 && list.size() == maxSize) {
    list.remove(maxSize - 1);
  }
  list.add(0,res1);
  update();
}
