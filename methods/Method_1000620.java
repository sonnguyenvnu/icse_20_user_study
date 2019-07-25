/** 
 * ?????max?min???,???????. <p/> ?max?min??0,???null
 * @return ??????
 */
public String next(){
  if (maxLen <= 0 || minLen <= 0 || minLen > maxLen)   return null;
  char[] buf=new char[R.random(minLen,maxLen)];
  for (int i=0; i < buf.length; i++)   buf[i]=CharGenerator.next();
  return new String(buf);
}
