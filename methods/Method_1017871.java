/** 
 * Auto wire an object that is created outside of spring but references spring @Autowire annotation
 */
public static Object autowire(Object obj){
  autowire(null,obj,true);
  return obj;
}
