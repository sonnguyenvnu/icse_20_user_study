/** 
 * ??????
 * @param obj ???Maplist
 * @param path ??
 */
public static void del(Object obj,String path){
  MaplRebuild rebuild=new MaplRebuild(obj);
  rebuild.remove(path);
}
