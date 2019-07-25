HTMLElement highlight(final HTMLElement pre,final ILine line,final int lineNr) throws IOException {
  final String style;
switch (line.getStatus()) {
case ICounter.NOT_COVERED:
    style=Styles.NOT_COVERED;
  break;
case ICounter.FULLY_COVERED:
style=Styles.FULLY_COVERED;
break;
case ICounter.PARTLY_COVERED:
style=Styles.PARTLY_COVERED;
break;
default :
return pre;
}
final String lineId="L" + Integer.toString(lineNr);
final ICounter branches=line.getBranchCounter();
switch (branches.getStatus()) {
case ICounter.NOT_COVERED:
return span(pre,lineId,style,Styles.BRANCH_NOT_COVERED,"All %2$d branches missed.",branches);
case ICounter.FULLY_COVERED:
return span(pre,lineId,style,Styles.BRANCH_FULLY_COVERED,"All %2$d branches covered.",branches);
case ICounter.PARTLY_COVERED:
return span(pre,lineId,style,Styles.BRANCH_PARTLY_COVERED,"%1$d of %2$d branches missed.",branches);
default :
return pre.span(style,lineId);
}
}
