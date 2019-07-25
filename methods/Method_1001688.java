private static String format(String s){
  final long delta=System.currentTimeMillis() - start;
  final long freeMemory=Runtime.getRuntime().freeMemory();
  final long maxMemory=Runtime.getRuntime().maxMemory();
  final long totalMemory=Runtime.getRuntime().totalMemory();
  final long usedMemory=totalMemory - freeMemory;
  final int threadActiveCount=Thread.activeCount();
  final StringBuilder sb=new StringBuilder();
  sb.append("(");
  sb.append(delta / 1000L);
  sb.append(".");
  sb.append(String.format("%03d",delta % 1000L));
  sb.append(" - ");
  final long total=totalMemory / 1024 / 1024;
  final long free=freeMemory / 1024 / 1024;
  sb.append(total);
  sb.append(" Mo) ");
  sb.append(free);
  sb.append(" Mo - ");
  sb.append(s);
  return sb.toString();
}
