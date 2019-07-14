/** 
 * <br> range = RANGE_USER_CIRCLE <br> id = APIJSONApplication.getInstance().getCurrentUserId()
 * @return
 */
public static MomentListFragment createInstance(){
  return createInstance(RANGE_USER_CIRCLE,APIJSONApplication.getInstance().getCurrentUserId());
}
