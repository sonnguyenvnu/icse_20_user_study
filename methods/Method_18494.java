/** 
 * Creates a size specification based on the supplied size and mode. The mode must always be one of the following: <ul> <li> {@link com.facebook.litho.SizeSpec#UNSPECIFIED}</li> <li> {@link com.facebook.litho.SizeSpec#EXACTLY}</li> </ul> <p><strong>Note:</strong> On API level 17 and lower, makeMeasureSpec's implementation was such that the order of arguments did not matter and overflow in either value could impact the resulting MeasureSpec. {@link android.widget.RelativeLayout} was affected by this bug.Apps targeting API levels greater than 17 will get the fixed, more strict behavior.</p>
 * @param size the size of the size specification
 * @param mode the mode of the size specification
 * @return the size specification based on size and mode
 */
public static int makeSizeSpec(int size,@MeasureSpecMode int mode){
  return View.MeasureSpec.makeMeasureSpec(size,mode);
}
