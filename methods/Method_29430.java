/** 
 * ?String ??? Integer???????0????? 
 */
public static Integer exceptionIfSmallerThan0(String originalStr) throws Exception {
  try {
    int num=Integer.parseInt(StringUtil.trimToEmpty(originalStr));
    if (num > 0)     return num;
 else     throw new Exception();
  }
 catch (  Exception e) {
    throw new Exception(originalStr + " is smaller than 0, or it is a  invalid parameter ");
  }
}
