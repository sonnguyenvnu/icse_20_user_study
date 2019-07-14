/** 
 * ????IP??????.
 * @param target ?????????????
 * @return ?????????????
 */
public static List<String> filterSensitiveIps(final List<String> target){
  final Map<String,String> fakeIpMap=new HashMap<>();
  final AtomicInteger step=new AtomicInteger();
  return Lists.transform(target,new Function<String,String>(){
    @Override public String apply(    final String input){
      Matcher matcher=Pattern.compile(IpUtils.IP_REGEX).matcher(input);
      String result=input;
      while (matcher.find()) {
        String realIp=matcher.group();
        String fakeIp;
        if (fakeIpMap.containsKey(realIp)) {
          fakeIp=fakeIpMap.get(realIp);
        }
 else {
          fakeIp=Joiner.on("").join(FAKE_IP_SAMPLE,step.incrementAndGet());
          fakeIpMap.put(realIp,fakeIp);
        }
        result=result.replace(realIp,fakeIp);
      }
      return result;
    }
  }
);
}
