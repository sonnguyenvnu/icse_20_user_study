/** 
 * ???????????
 * @param price
 * @param formatType ???????
 * @return
 */
public static String getPrice(double price,int formatType){
  String s=new DecimalFormat("#########0.00").format(price);
switch (formatType) {
case PRICE_FORMAT_PREFIX:
    return PRICE_FORMATS[PRICE_FORMAT_PREFIX] + s;
case PRICE_FORMAT_SUFFIX:
  return s + PRICE_FORMATS[PRICE_FORMAT_SUFFIX];
case PRICE_FORMAT_PREFIX_WITH_BLANK:
return PRICE_FORMATS[PRICE_FORMAT_PREFIX_WITH_BLANK] + s;
case PRICE_FORMAT_SUFFIX_WITH_BLANK:
return s + PRICE_FORMATS[PRICE_FORMAT_SUFFIX_WITH_BLANK];
default :
return s;
}
}
