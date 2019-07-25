/** 
 * ???package???package??@Table???Pojo??migration
 * @param dao Dao??
 * @param packageName ???package??
 * @param add ????????
 * @param del ????????
 * @param checkIndex ??????
 */
public static void migration(Dao dao,String packageName,boolean add,boolean del,boolean checkIndex){
  for (  Class<?> klass : Scans.me().scanPackage(packageName)) {
    if (Mirror.me(klass).getAnnotation(Table.class) != null) {
      migration(dao,klass,add,del,checkIndex,null);
    }
  }
}
