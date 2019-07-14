/** 
 * MAP???????NameValuePair??
 * @param properties MAP????
 * @return NameValuePair????
 */
private static NameValuePair[] generatNameValuePair(SortedMap<String,String> properties){
  NameValuePair[] nameValuePair=new NameValuePair[properties.size()];
  int i=0;
  for (  Map.Entry<String,String> entry : properties.entrySet()) {
    nameValuePair[i++]=new NameValuePair(entry.getKey(),entry.getValue());
  }
  return nameValuePair;
}
