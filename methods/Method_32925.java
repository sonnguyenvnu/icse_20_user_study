/** 
 * Inform the attached pty of the new size and reflow or initialize the emulator. 
 */
public void updateSize(int columns,int rows){
  if (mEmulator == null) {
    initializeEmulator(columns,rows);
  }
 else {
    JNI.setPtyWindowSize(mTerminalFileDescriptor,rows,columns);
    mEmulator.resize(columns,rows);
  }
}
