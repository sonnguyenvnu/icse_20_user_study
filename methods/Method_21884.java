@OnClick(R.id.button_change_orientation) void onButtonChangeOrientation(){
  widget.setTitleAnimationOrientation(widget.getTitleAnimationOrientation() == MaterialCalendarView.VERTICAL ? MaterialCalendarView.HORIZONTAL : MaterialCalendarView.VERTICAL);
  Toast.makeText(this,widget.getTitleAnimationOrientation() == MaterialCalendarView.VERTICAL ? "Vertical" : "Horizontal",Toast.LENGTH_SHORT).show();
}
