/** 
 * line format: CPU001,CPU 1 bx-50-13,User%,Sys%,Wait%,Idle% CPU002,CPU 2 bx-50-13,User%,Sys%,Wait%,Idle% CPU_ALL,CPU Total bx-50-13,User%,Sys%,Wait%,Idle%,Busy,CPUs CPU001,T0001,1.8,0.9,4.5,92.9 CPU002,T0001,3.6,1.8,0.0,94.6 CPU_ALL,T0001,2.1,1.3,0.6,95.9,,16
 */
public void parse(String line,String timeKey) throws Exception {
  if (line.startsWith(FLAG)) {
    String[] items=line.split(",",6);
    if (items.length != 6) {
      return;
    }
    if (!items[1].equals(timeKey)) {
      return;
    }
    Usage usage=new Usage();
    usage.setUser(NumberUtils.toFloat(items[2]));
    usage.setSys(NumberUtils.toFloat(items[3]));
    usage.setWait(NumberUtils.toFloat(items[4]));
    if (CPU_ALL.equals(items[0])) {
      allUsage=usage;
    }
 else {
      usage.setName(items[0]);
      cpuList.add(usage);
    }
  }
}
