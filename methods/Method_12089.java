public List<String> scanKeys(String key){
  Jedis jedis=null;
  try {
    jedis=jedisPool.getResource();
    List<String> keys=new ArrayList<String>();
    String cursor="0";
    ScanParams sp=new ScanParams();
    sp.match("*" + key + "*");
    sp.count(100);
    do {
      ScanResult<String> ret=jedis.scan(cursor,sp);
      List<String> result=ret.getResult();
      if (result != null && result.size() > 0) {
        keys.addAll(result);
      }
      cursor=ret.getStringCursor();
    }
 while (!cursor.equals("0"));
    return keys;
  }
  finally {
    if (jedis != null) {
      jedis.close();
    }
  }
}
