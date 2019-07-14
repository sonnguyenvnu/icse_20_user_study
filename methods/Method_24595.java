/** 
 * Detach this breakpoint from the VM. Deletes the {@link BreakpointRequest}.
 */
public void detach(){
  if (bpr != null) {
    try {
      dbg.vm().eventRequestManager().deleteEventRequest(bpr);
    }
 catch (    VMDisconnectedException ignore) {
    }
    bpr=null;
  }
}
