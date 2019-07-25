/** 
 * ???package???package??@Table???Pojo??migration
 * @param dao Dao??
 * @param packageName ???package??
 * @param add ????????
 * @param del ????????
 * @param checkIndex ??????
 * @param nameTable ???????
 */
public static void migration(Dao dao,String packageName,boolean add,boolean del,boolean checkIndex,Object nameTable){
  for (  Class<?> klass : Scans.me().scanPackage(packageName)) {
    if (Mirror.me(klass).getAnnotation(Table.class) != null) {
      migration(dao,klass,add,del,checkIndex,nameTable);
    }
  }
}
