@Override public void initEvent(){
  for (int i=0; i < ivSettings.length; i++) {
    final int which=i;
    ivSettings[which].setOnClickListener(new OnClickListener(){
      @Override public void onClick(      View v){
        isSettingChanged=true;
        setSwitch(which,!settings[which]);
      }
    }
);
  }
}
