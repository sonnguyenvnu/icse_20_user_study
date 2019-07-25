private static void prepare(Properties main,Properties base,File trans,boolean utf8) throws IOException {
  SortedProperties p=load(trans.getAbsolutePath(),utf8);
  Properties oldTranslations=new Properties();
  for (  Object k : base.keySet()) {
    String key=(String)k;
    String m=base.getProperty(key);
    String t=p.getProperty(key);
    if (t != null && !t.startsWith("#")) {
      oldTranslations.setProperty(m,t);
    }
  }
  HashSet<String> toTranslate=new HashSet<>();
  for (  Object k : main.keySet()) {
    String key=(String)k;
    String now=main.getProperty(key);
    if (!p.containsKey(key)) {
      String t=oldTranslations.getProperty(now);
      if (t == null) {
        t="#" + now;
        p.put(key,t);
      }
 else {
        p.put(key,t);
      }
    }
 else {
      String t=p.getProperty(key);
      String last=base.getProperty(key);
      if (t.startsWith("#") && !t.startsWith("##")) {
        t=oldTranslations.getProperty(now);
        if (t == null) {
          t="#" + now;
        }
        p.put(key,t);
      }
 else       if (last != null && !last.equals(now)) {
        t=oldTranslations.getProperty(now);
        if (t == null) {
          System.out.println(trans.getName() + ": key " + key + " changed, please review; last=" + last + " now=" + now);
          String old=p.getProperty(key);
          t="#" + now + " #" + old;
          p.put(key,t);
        }
 else {
          p.put(key,t);
        }
      }
    }
  }
  for (  String key : toTranslate) {
    String now=main.getProperty(key);
    String t;
    System.out.println(trans.getName() + ": key " + key + " not found in translation file; added dummy # 'translation'");
    t="#" + now;
    p.put(key,t);
  }
  for (  Object k : new ArrayList<>(p.keySet())) {
    String key=(String)k;
    if (!main.containsKey(key)) {
      p.remove(key);
    }
  }
  store(p,trans.getAbsolutePath(),utf8);
}
