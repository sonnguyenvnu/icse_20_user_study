@Override public void init(int instanceId,StateVariableAllowedValueRange volumeRange){
  this.instanceId=instanceId;
  muteButton.setVerticalTextPosition(JToggleButton.BOTTOM);
  muteButton.setHorizontalTextPosition(JToggleButton.CENTER);
  muteButton.setFocusable(false);
  muteButton.setPreferredSize(new Dimension(60,50));
  muteButton.addItemListener(new ItemListener(){
    public void itemStateChanged(    ItemEvent itemEvent){
      int state=itemEvent.getStateChange();
      if (state == ItemEvent.SELECTED) {
        muteButton.setIcon(ICON_MUTE_ON);
        volumeSlider.setEnabled(false);
      }
 else {
        muteButton.setIcon(ICON_MUTE_OFF);
        volumeSlider.setEnabled(true);
      }
    }
  }
);
  muteButton.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      presenter.onMuteSelected(getInstanceId(),muteButton.isSelected());
    }
  }
);
  int minVolume=0;
  int maxVolume=100;
  if (volumeRange != null) {
    minVolume=new Long(volumeRange.getMinimum()).intValue();
    maxVolume=new Long(volumeRange.getMaximum()).intValue();
  }
  volumeSlider=new JSlider(JSlider.HORIZONTAL,minVolume,maxVolume,maxVolume / 2);
  volumeSlider.setBorder(BorderFactory.createTitledBorder("Volume"));
  volumeSlider.setMajorTickSpacing(maxVolume / 5);
  volumeSlider.setMinorTickSpacing(5);
  volumeSlider.setPaintTicks(true);
  volumeSlider.setPaintLabels(true);
  volumeSlider.addChangeListener(new ChangeListener(){
    public void stateChanged(    ChangeEvent e){
      JSlider source=(JSlider)e.getSource();
      if (!source.getValueIsAdjusting()) {
        int newVolume=source.getValue();
        presenter.onVolumeSelected(getInstanceId(),newVolume);
      }
    }
  }
);
  setLayout(new BorderLayout());
  add(muteButton,BorderLayout.WEST);
  add(volumeSlider,BorderLayout.CENTER);
  setPreferredSize(new Dimension(300,80));
}
