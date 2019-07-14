/** 
 * @param param ?? “?? v 7685 vn 616” ???
 * @return
 */
public static Item create(String param){
  if (param == null)   return null;
  String mark="\\s";
  if (param.indexOf('\t') > 0)   mark="\t";
  String[] array=param.split(mark);
  return create(array);
}
