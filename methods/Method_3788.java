@Override public View onFocusSearchFailed(View focused,int focusDirection,RecyclerView.Recycler recycler,RecyclerView.State state){
  View prevFocusedChild=findContainingItemView(focused);
  if (prevFocusedChild == null) {
    return null;
  }
  LayoutParams lp=(LayoutParams)prevFocusedChild.getLayoutParams();
  final int prevSpanStart=lp.mSpanIndex;
  final int prevSpanEnd=lp.mSpanIndex + lp.mSpanSize;
  View view=super.onFocusSearchFailed(focused,focusDirection,recycler,state);
  if (view == null) {
    return null;
  }
  final int layoutDir=convertFocusDirectionToLayoutDirection(focusDirection);
  final boolean ascend=(layoutDir == LayoutState.LAYOUT_END) != mShouldReverseLayout;
  final int start, inc, limit;
  if (ascend) {
    start=getChildCount() - 1;
    inc=-1;
    limit=-1;
  }
 else {
    start=0;
    inc=1;
    limit=getChildCount();
  }
  final boolean preferLastSpan=mOrientation == VERTICAL && isLayoutRTL();
  View focusableWeakCandidate=null;
  int focusableWeakCandidateSpanIndex=-1;
  int focusableWeakCandidateOverlap=0;
  View unfocusableWeakCandidate=null;
  int unfocusableWeakCandidateSpanIndex=-1;
  int unfocusableWeakCandidateOverlap=0;
  int focusableSpanGroupIndex=getSpanGroupIndex(recycler,state,start);
  for (int i=start; i != limit; i+=inc) {
    int spanGroupIndex=getSpanGroupIndex(recycler,state,i);
    View candidate=getChildAt(i);
    if (candidate == prevFocusedChild) {
      break;
    }
    if (candidate.hasFocusable() && spanGroupIndex != focusableSpanGroupIndex) {
      if (focusableWeakCandidate != null) {
        break;
      }
      continue;
    }
    final LayoutParams candidateLp=(LayoutParams)candidate.getLayoutParams();
    final int candidateStart=candidateLp.mSpanIndex;
    final int candidateEnd=candidateLp.mSpanIndex + candidateLp.mSpanSize;
    if (candidate.hasFocusable() && candidateStart == prevSpanStart && candidateEnd == prevSpanEnd) {
      return candidate;
    }
    boolean assignAsWeek=false;
    if ((candidate.hasFocusable() && focusableWeakCandidate == null) || (!candidate.hasFocusable() && unfocusableWeakCandidate == null)) {
      assignAsWeek=true;
    }
 else {
      int maxStart=Math.max(candidateStart,prevSpanStart);
      int minEnd=Math.min(candidateEnd,prevSpanEnd);
      int overlap=minEnd - maxStart;
      if (candidate.hasFocusable()) {
        if (overlap > focusableWeakCandidateOverlap) {
          assignAsWeek=true;
        }
 else         if (overlap == focusableWeakCandidateOverlap && preferLastSpan == (candidateStart > focusableWeakCandidateSpanIndex)) {
          assignAsWeek=true;
        }
      }
 else       if (focusableWeakCandidate == null && isViewPartiallyVisible(candidate,false,true)) {
        if (overlap > unfocusableWeakCandidateOverlap) {
          assignAsWeek=true;
        }
 else         if (overlap == unfocusableWeakCandidateOverlap && preferLastSpan == (candidateStart > unfocusableWeakCandidateSpanIndex)) {
          assignAsWeek=true;
        }
      }
    }
    if (assignAsWeek) {
      if (candidate.hasFocusable()) {
        focusableWeakCandidate=candidate;
        focusableWeakCandidateSpanIndex=candidateLp.mSpanIndex;
        focusableWeakCandidateOverlap=Math.min(candidateEnd,prevSpanEnd) - Math.max(candidateStart,prevSpanStart);
      }
 else {
        unfocusableWeakCandidate=candidate;
        unfocusableWeakCandidateSpanIndex=candidateLp.mSpanIndex;
        unfocusableWeakCandidateOverlap=Math.min(candidateEnd,prevSpanEnd) - Math.max(candidateStart,prevSpanStart);
      }
    }
  }
  return (focusableWeakCandidate != null) ? focusableWeakCandidate : unfocusableWeakCandidate;
}
