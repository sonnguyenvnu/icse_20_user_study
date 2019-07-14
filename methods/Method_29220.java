@Override public List<MachineInfo> getMachineInfoByType(TypeEnum typeEnum){
  try {
    return machineDao.getMachineInfoByType(typeEnum.getType());
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
    return Collections.emptyList();
  }
}
