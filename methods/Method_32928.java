/** 
 * Cleanup resources when the process exits. 
 */
void cleanupResources(int exitStatus){
synchronized (this) {
    mShellPid=-1;
    mShellExitStatus=exitStatus;
  }
  mTerminalToProcessIOQueue.close();
  mProcessToTerminalIOQueue.close();
  JNI.close(mTerminalFileDescriptor);
}
