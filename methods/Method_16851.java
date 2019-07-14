public static void print(List<Interval> list){
  if (list == null) {
    System.out.println("null");
    return;
  }
  StringBuilder sb=new StringBuilder();
  for (  Interval interval : list) {
    sb.append("[").append(interval.start).append(",").append(interval.end).append("],");
  }
  System.out.println(sb.substring(0,sb.length() - 1));
}
