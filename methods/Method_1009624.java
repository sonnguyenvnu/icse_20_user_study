@Override public void init(int instanceId){
  this.instanceId=instanceId;
  this.viewStateMachine=StateMachineBuilder.build(InstanceViewStateMachine.class,NoMediaPresent.class,new Class[]{InstanceViewImpl.class},new Object[]{this});
  playerPanel.getPreviousButton().addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent actionEvent){
      presenter.onPreviousSelected(getInstanceId());
    }
  }
);
  playerPanel.getNextButton().addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent actionEvent){
      presenter.onNextSelected(getInstanceId());
    }
  }
);
  playerPanel.getFwdButton().addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent actionEvent){
      presenter.onSeekSelected(getInstanceId(),15,true);
    }
  }
);
  playerPanel.getRewButton().addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent actionEvent){
      presenter.onSeekSelected(getInstanceId(),15,false);
    }
  }
);
  playerPanel.getPauseButton().addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent actionEvent){
      presenter.onPauseSelected(getInstanceId());
    }
  }
);
  playerPanel.getStopButton().addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent actionEvent){
      presenter.onStopSelected(getInstanceId());
    }
  }
);
  playerPanel.getPlayButton().addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent actionEvent){
      presenter.onPlaySelected(getInstanceId());
    }
  }
);
  playModeSpinnerItemListener=new ItemListener(){
    @Override public void itemStateChanged(    ItemEvent event){
      if (event.getStateChange() == ItemEvent.SELECTED) {
        final PlayMode playMode=(PlayMode)event.getItem();
        presenter.onSetPlayModeSelected(getInstanceId(),playMode);
      }
    }
  }
;
  playModePanel.getPlayModeSpinner().addItemListener(playModeSpinnerItemListener);
  progressPanel.getPositionSlider().addChangeListener(new ChangeListener(){
    @Override public void stateChanged(    ChangeEvent e){
      final JSlider source=(JSlider)e.getSource();
      if (source.getValueIsAdjusting())       return;
      PositionInfo positionInfo=getProgressPanel().getPositionInfo();
      if (positionInfo != null) {
        int newValue=source.getValue();
        double seekTargetSeconds=newValue * positionInfo.getTrackDurationSeconds() / 100;
        final String targetTime=ModelUtil.toTimeString(new Long(Math.round(seekTargetSeconds)).intValue());
        presenter.onSeekSelected(getInstanceId(),targetTime);
      }
    }
  }
);
  uriPanel.getSetButton().addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent actionEvent){
      final String uri=uriPanel.getUriTextField().getText();
      if (uri == null || uri.length() == 0)       return;
      try {
        URI.create(uri);
      }
 catch (      IllegalArgumentException ex) {
        AVTransportControlPoint.LOGGER.warning("Invalid URI, can't set on AVTransport: " + uri);
      }
      presenter.onSetAVTransportURISelected(getInstanceId(),uri);
    }
  }
);
  setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
  add(playerPanel);
  add(playModePanel);
  add(progressPanel);
  add(uriPanel);
}
