/** 
 * @param containerView ??view?container
 * @param conversation
 */
@ExtContextMenuItem(title="Example") public void image(View containerView,Conversation conversation){
  FrameLayout frameLayout=(FrameLayout)containerView;
  View view=LayoutInflater.from(context).inflate(R.layout.conversatioin_ext_example_layout,frameLayout,false);
  frameLayout.addView(view);
  extension.disableHideOnScroll();
  view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener(){
    @Override public void onClick(    View v){
      extension.reset();
    }
  }
);
}
