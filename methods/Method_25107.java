@Override public void onActivityCreated(@Nullable Bundle savedInstanceState){
  super.onActivityCreated(savedInstanceState);
  menus.add(menuDown);
  menus.add(menuRed);
  menus.add(menuYellow);
  menus.add(menuGreen);
  menus.add(menuBlue);
  menus.add(menuLabelsRight);
  menuYellow.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener(){
    @Override public void onMenuToggle(    boolean opened){
      String text;
      if (opened) {
        text="Menu opened";
      }
 else {
        text="Menu closed";
      }
      Toast.makeText(getActivity(),text,Toast.LENGTH_SHORT).show();
    }
  }
);
  fab1.setOnClickListener(clickListener);
  fab2.setOnClickListener(clickListener);
  fab3.setOnClickListener(clickListener);
  int delay=400;
  for (  final FloatingActionMenu menu : menus) {
    mUiHandler.postDelayed(new Runnable(){
      @Override public void run(){
        menu.showMenuButton(true);
      }
    }
,delay);
    delay+=150;
  }
  new Handler().postDelayed(new Runnable(){
    @Override public void run(){
      fabEdit.show(true);
    }
  }
,delay + 150);
  menuRed.setOnMenuButtonClickListener(new View.OnClickListener(){
    @Override public void onClick(    View v){
      if (menuRed.isOpened()) {
        Toast.makeText(getActivity(),menuRed.getMenuButtonLabelText(),Toast.LENGTH_SHORT).show();
      }
      menuRed.toggle(true);
    }
  }
);
  createCustomAnimation();
}
