@Override @SuppressWarnings("unchecked") public void extract(MapSession target,String argument,ValueCollector collector){
  String principalName=target.getAttribute(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME);
  if (principalName != null) {
    collector.addObject(principalName);
  }
}
