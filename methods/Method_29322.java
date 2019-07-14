/** 
 * line format: DISKBUSY,Disk %Busy iZ256oe4w5bZ,xvda,xvda1,xvdb,xvdb1 DISKREAD,Disk Read KB/s iZ256oe4w5bZ,xvda,xvda1,xvdb,xvdb1 DISKWRITE,Disk Write KB/s iZ256oe4w5bZ,xvda,xvda1,xvdb,xvdb1 DISKXFER,Disk transfers per second iZ256oe4w5bZ,xvda,xvda1,xvdb,xvdb1 DISKBSIZE,Disk Block Size iZ256oe4w5bZ,xvda,xvda1,xvdb,xvdb1 DISKBUSY,T0001,0.0,0.0,0.0,0.0 DISKREAD,T0001,0.0,0.0,0.0,0.0 DISKWRITE,T0001,0.0,0.0,0.0,0.0 DISKXFER,T0001,0.0,0.0,0.0,0.0 DISKBSIZE,T0001,0.0,0.0,0.0,0.0 BBBP,173,/bin/df-m,"ddev/xvda1         20158 16018      3117  84% /"
 */
public void parse(String line,String timeKey) throws Exception {
  if (line.startsWith(FLAG)) {
    String[] items=line.split(",");
    if (!items[1].equals(timeKey)) {
      DiskUsageType type=DiskUsageType.getType(items[0]);
      if (type == null) {
        return;
      }
      List<Usage> list=diskMap.get(type);
      if (list == null) {
        list=new ArrayList<Usage>();
        diskMap.put(type,list);
      }
      for (int i=2; i < items.length; ++i) {
        Usage usage=new Usage();
        usage.setDiskUsageTyp(type);
        usage.setName(items[i]);
        list.add(usage);
      }
    }
 else {
      DiskUsageType type=DiskUsageType.getType(items[0]);
      if (type == null) {
        return;
      }
      List<Usage> list=diskMap.get(type);
      if (list == null) {
        return;
      }
      for (int i=2; i < items.length; ++i) {
        float value=NumberUtils.toFloat(items[i]);
        if (value > 0) {
          list.get(i - 2).setValue(value);
        }
      }
    }
  }
 else   if (PATTERN.matcher(line).find()) {
    String[] tmp=line.split(",\"");
    if (tmp.length > 0) {
      String[] item=tmp[tmp.length - 1].split("\\s+");
      if (item.length != 6 || !item[4].contains("%")) {
        return;
      }
      List<Usage> list=diskMap.get(DiskUsageType.busy);
      String[] tp=item[0].split("/");
      String mount=tp[tp.length - 1];
      for (      Usage usage : list) {
        if (usage.getName().equals(mount)) {
          List<Usage> spaceList=diskMap.get(DiskUsageType.space);
          if (spaceList == null) {
            spaceList=new ArrayList<Usage>();
            diskMap.put(DiskUsageType.space,spaceList);
          }
          Usage spaceUsage=new Usage();
          spaceUsage.setDiskUsageTyp(DiskUsageType.space);
          spaceUsage.setName(usage.getName());
          spaceUsage.setValue(NumberUtils.toFloat(item[4].split("%")[0]));
          spaceList.add(spaceUsage);
        }
      }
    }
  }
}
