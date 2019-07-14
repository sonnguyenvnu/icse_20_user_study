/** 
 * ????????ip1:port1 ip2:port2
 * @param instanceList
 * @return
 */
public static String assembleInstance(List<InstanceInfo> instanceList){
  if (instanceList.isEmpty()) {
    return null;
  }
  StringBuilder instanceBuilder=new StringBuilder();
  for (int i=0; i < instanceList.size(); i++) {
    InstanceInfo instanceInfo=instanceList.get(i);
    if (instanceInfo.isOffline()) {
      continue;
    }
    if (i > 0) {
      instanceBuilder.append(" ");
    }
    instanceBuilder.append(instanceInfo.getIp()).append(":").append(instanceInfo.getPort());
  }
  return StringUtils.trim(instanceBuilder.toString());
}
