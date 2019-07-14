/** 
 * Returns the first view starting from fromIndex to toIndex in views whose bounds lie within its parent bounds based on the provided preferredBoundFlags. If no match is found based on the preferred flags, and a nonzero acceptableBoundFlags is specified, the last view whose bounds lie within its parent view based on the acceptableBoundFlags is returned. If no such view is found based on either of these two flags, null is returned.
 * @param fromIndex The view position index to start the search from.
 * @param toIndex The view position index to end the search at.
 * @param preferredBoundFlags The flags indicating the preferred match. Once a match is foundbased on this flag, that view is returned instantly.
 * @param acceptableBoundFlags The flags indicating the acceptable match if no preferred matchis found. If so, and if acceptableBoundFlags is non-zero, the last matching acceptable view is returned. Otherwise, null is returned.
 * @return The first view that satisfies acceptableBoundFlags or the last view satisfyingacceptableBoundFlags boundary conditions.
 */
View findOneViewWithinBoundFlags(int fromIndex,int toIndex,@ViewBounds int preferredBoundFlags,@ViewBounds int acceptableBoundFlags){
  final int start=mCallback.getParentStart();
  final int end=mCallback.getParentEnd();
  final int next=toIndex > fromIndex ? 1 : -1;
  View acceptableMatch=null;
  for (int i=fromIndex; i != toIndex; i+=next) {
    final View child=mCallback.getChildAt(i);
    final int childStart=mCallback.getChildStart(child);
    final int childEnd=mCallback.getChildEnd(child);
    mBoundFlags.setBounds(start,end,childStart,childEnd);
    if (preferredBoundFlags != 0) {
      mBoundFlags.resetFlags();
      mBoundFlags.addFlags(preferredBoundFlags);
      if (mBoundFlags.boundsMatch()) {
        return child;
      }
    }
    if (acceptableBoundFlags != 0) {
      mBoundFlags.resetFlags();
      mBoundFlags.addFlags(acceptableBoundFlags);
      if (mBoundFlags.boundsMatch()) {
        acceptableMatch=child;
      }
    }
  }
  return acceptableMatch;
}
