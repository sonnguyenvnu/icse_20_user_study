/** 
 * Get HostPerformanceEntity[cpuUsage, memUsage, load] by ssh.<br> ???????????????????????
 * @param ip
 * @param userName
 * @param password
 * @throws Exception
 * @since 1.0.0
 */
public static MachineStats getMachineInfo(String ip,int port,String userName,String password) throws SSHException {
  if (StringUtil.isBlank(ip)) {
    try {
      throw new IllegalParamException("Param ip is empty!");
    }
 catch (    IllegalParamException e) {
      throw new SSHException(e.getMessage(),e);
    }
  }
  port=IntegerUtil.defaultIfSmallerThan0(port,ConstUtils.SSH_PORT_DEFAULT);
  final MachineStats systemPerformanceEntity=new MachineStats();
  systemPerformanceEntity.setIp(ip);
  sshTemplate.execute(ip,port,userName,password,new SSHCallback(){
    public Result call(    SSHSession session){
      session.executeCommand(COMMAND_TOP,new DefaultLineProcessor(){
        public void process(        String line,        int lineNum) throws Exception {
          if (lineNum > 5) {
            return;
          }
          if (1 == lineNum) {
            int loadAverageIndex=line.indexOf(LOAD_AVERAGE_STRING);
            String loadAverages=line.substring(loadAverageIndex).replace(LOAD_AVERAGE_STRING,EMPTY_STRING);
            String[] loadAverageArray=loadAverages.split(",");
            if (3 == loadAverageArray.length) {
              systemPerformanceEntity.setLoad(StringUtil.trimToEmpty(loadAverageArray[0]));
            }
          }
 else           if (3 == lineNum) {
            double cpuUs=getUsCpu(line);
            systemPerformanceEntity.setCpuUsage(String.valueOf(cpuUs));
          }
        }
      }
);
      session.executeCommand(COMMAND_MEM,new LineProcessor(){
        public void process(        String line,        int lineNum) throws Exception {
          if (line.contains(MEM_TOTAL)) {
            totalMem=matchMemLineNumber(line).trim();
          }
 else           if (line.contains(MEM_FREE)) {
            freeMem=matchMemLineNumber(line).trim();
          }
 else           if (line.contains(MEM_BUFFERS)) {
            buffersMem=matchMemLineNumber(line).trim();
          }
 else           if (line.contains(MEM_CACHED)) {
            cachedMem=matchMemLineNumber(line).trim();
          }
        }
        public void finish(){
          if (!StringUtil.isBlank(totalMem,freeMem,buffersMem)) {
            Long totalMemLong=NumberUtils.toLong(totalMem);
            Long freeMemLong=NumberUtils.toLong(freeMem);
            Long buffersMemLong=NumberUtils.toLong(buffersMem);
            Long cachedMemLong=NumberUtils.toLong(cachedMem);
            Long usedMemFree=freeMemLong + buffersMemLong + cachedMemLong;
            Double memoryUsage=1 - (NumberUtils.toDouble(usedMemFree.toString()) / NumberUtils.toDouble(totalMemLong.toString()) / 1.0);
            systemPerformanceEntity.setMemoryTotal(String.valueOf(totalMemLong));
            systemPerformanceEntity.setMemoryFree(String.valueOf(usedMemFree));
            DecimalFormat df=new DecimalFormat("0.00");
            systemPerformanceEntity.setMemoryUsageRatio(df.format(memoryUsage * 100));
          }
        }
      }
);
      session.executeCommand(COMMAND_DF_LH,new LineProcessor(){
        public void process(        String line,        int lineNum) throws Exception {
          if (lineNum == 1) {
            return;
          }
          line=line.replaceAll(" {1,}",WORD_SEPARATOR);
          String[] lineArray=line.split(WORD_SEPARATOR);
          if (6 == lineArray.length) {
            String diskUsage=lineArray[4];
            String mountedOn=lineArray[5];
            diskUsageMap.put(mountedOn,diskUsage);
          }
        }
        public void finish(){
          systemPerformanceEntity.setDiskUsageMap(diskUsageMap);
        }
      }
);
      return null;
    }
  }
);
  Double traffic=0.0;
  systemPerformanceEntity.setTraffic(traffic.toString());
  return systemPerformanceEntity;
}
