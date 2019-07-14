private boolean action(int what,@NonNull TextView widget,@NonNull Spannable buffer){
  Layout layout=widget.getLayout();
  int padding=widget.getTotalPaddingTop() + widget.getTotalPaddingBottom();
  int areaTop=widget.getScrollY();
  int areaBot=areaTop + widget.getHeight() - padding;
  int lineTop=layout.getLineForVertical(areaTop);
  int lineBot=layout.getLineForVertical(areaBot);
  int first=layout.getLineStart(lineTop);
  int last=layout.getLineEnd(lineBot);
  ClickableSpan[] candidates=buffer.getSpans(first,last,ClickableSpan.class);
  int a=Selection.getSelectionStart(buffer);
  int b=Selection.getSelectionEnd(buffer);
  int selStart=Math.min(a,b);
  int selEnd=Math.max(a,b);
  if (selStart < 0) {
    if (buffer.getSpanStart(FROM_BELOW) >= 0) {
      selStart=selEnd=buffer.length();
    }
  }
  if (selStart > last)   selStart=selEnd=Integer.MAX_VALUE;
  if (selEnd < first)   selStart=selEnd=-1;
switch (what) {
case CLICK:
    if (selStart == selEnd) {
      return false;
    }
  ClickableSpan[] link=buffer.getSpans(selStart,selEnd,ClickableSpan.class);
if (link.length != 1) return false;
link[0].onClick(widget);
break;
case UP:
int bestStart, bestEnd;
bestStart=-1;
bestEnd=-1;
for (int i=0; i < candidates.length; i++) {
int end=buffer.getSpanEnd(candidates[i]);
if (end < selEnd || selStart == selEnd) {
if (end > bestEnd) {
bestStart=buffer.getSpanStart(candidates[i]);
bestEnd=end;
}
}
}
if (bestStart >= 0) {
Selection.setSelection(buffer,bestEnd,bestStart);
return true;
}
break;
case DOWN:
bestStart=Integer.MAX_VALUE;
bestEnd=Integer.MAX_VALUE;
for (int i=0; i < candidates.length; i++) {
int start=buffer.getSpanStart(candidates[i]);
if (start > selStart || selStart == selEnd) {
if (start < bestStart) {
bestStart=start;
bestEnd=buffer.getSpanEnd(candidates[i]);
}
}
}
if (bestEnd < Integer.MAX_VALUE) {
Selection.setSelection(buffer,bestStart,bestEnd);
return true;
}
break;
}
return false;
}
