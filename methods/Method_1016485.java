public static Map<String,BObject> transcode(Map<String,byte[]> map){
  Map<String,BObject> m=new HashMap<String,BObject>();
  for (  Map.Entry<String,byte[]> entry : map.entrySet())   m.put(entry.getKey(),new BDecoder.BStringObject(entry.getValue()));
  return m;
}
