/** 
 * Update the width/height spec. This is useful if you are currently detached and are responding to a configuration change. If you are currently attached then the HostView is the source of truth for width/height, so this call will be ignored.
 */
public void setSizeSpec(int widthSpec,int heightSpec){
  setSizeSpec(widthSpec,heightSpec,null);
}
