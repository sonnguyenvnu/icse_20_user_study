public void init(DefaultFFTData fftData){
  this.fftData=fftData;
  this.setLayout(new BorderLayout());
  JPanel connectionPanel=new JPanel(new FlowLayout());
  JButton buttonConnect=new JButton("connect");
  buttonConnect.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      if (oscForwarder.isConnected())       oscForwarder.disconnect();
      boolean connectionSuccessful=connect();
      JOptionPane.showMessageDialog(OSCForwardMask.this,"forwarding OSC: " + (connectionSuccessful ? "successful" : "failed"),"OSC forwarder",JOptionPane.INFORMATION_MESSAGE);
    }
  }
);
  ipText=new JTextField(config.getPref(Config.osc_settings,String.valueOf(Config.osc_settings_params.ip)));
  ipText.getDocument().addDocumentListener(new DocumentListener(){
    public void save(){
      if (config != null) {
        config.setPref(Config.osc_settings,String.valueOf(Config.osc_settings_params.ip),ipText.getText());
        config.store();
      }
    }
    @Override public void changedUpdate(    DocumentEvent arg0){
      save();
    }
    @Override public void insertUpdate(    DocumentEvent arg0){
      save();
    }
    @Override public void removeUpdate(    DocumentEvent arg0){
      save();
    }
  }
);
  portText=new JTextField(config.getPref(Config.osc_settings,String.valueOf(Config.osc_settings_params.port)));
  portText.getDocument().addDocumentListener(new DocumentListener(){
    public void save(){
      if (config != null) {
        config.setPref(Config.osc_settings,String.valueOf(Config.osc_settings_params.port),portText.getText());
        config.store();
      }
    }
    @Override public void changedUpdate(    DocumentEvent arg0){
      save();
    }
    @Override public void insertUpdate(    DocumentEvent arg0){
      save();
    }
    @Override public void removeUpdate(    DocumentEvent arg0){
      save();
    }
  }
);
  ipText.setPreferredSize(new Dimension((int)(oscWidth / 2.3),14));
  JPanel ipPanel=new JPanel(new FlowLayout());
  ipPanel.add(new JLabel("ip:"));
  ipPanel.add(ipText);
  JPanel portPanel=new JPanel(new FlowLayout());
  portPanel.add(new JLabel("port:"));
  portPanel.add(portText);
  connectionPanel.add(ipPanel);
  connectionPanel.add(portPanel);
  connectionPanel.add(buttonConnect);
  JPanel settingsPanel=new JPanel(new GridLayout(fftData.bins,fftData.numChannels));
  this.add(connectionPanel,BorderLayout.NORTH);
  minSliders=new JSlider[fftData.bins * fftData.numChannels];
  maxSliders=new JSlider[fftData.bins * fftData.numChannels];
  outputs=new JTextField[fftData.bins * fftData.numChannels];
  currentValLabels=new JLabel[fftData.bins * fftData.numChannels];
  thresholdRenderers=new ThresholdRenderer[fftData.bins * fftData.numChannels];
  for (int b=0; b < fftData.bins; b++) {
    for (int c=0; c < fftData.numChannels; c++) {
      int ci=c * fftData.bins + b;
      String msgAsString=config.getPref(Config.osc_settings,String.valueOf(Config.osc_settings_params.address) + ci);
      double minVal=Double.valueOf(config.getPref(Config.osc_settings,String.valueOf(Config.osc_settings_params.address) + ci + "min"));
      double maxVal=Double.valueOf(config.getPref(Config.osc_settings,String.valueOf(Config.osc_settings_params.address) + ci + "max"));
      minSliders[ci]=new JSlider(SwingConstants.HORIZONTAL,1,500,(int)((minVal + 2d) * 100d));
      maxSliders[ci]=new JSlider(SwingConstants.HORIZONTAL,1,500,(int)((maxVal + 2d) * 100d));
      outputs[ci]=new JTextField(msgAsString);
      thresholdRenderers[ci]=new ThresholdRenderer(new Dimension(oscWidth,40),(float)minVal,(float)maxVal,0f);
      currentValLabels[ci]=new JLabel("0");
      JPanel sliderPanel=new JPanel();
      sliderPanel.setBorder(BorderFactory.createTitledBorder((c == 0 ? "left" : "right") + " " + fwdTask.getNfbServer().getCurrentFeedbackSettings().binLabels[b]));
      sliderPanel.setLayout(new BorderLayout());
      JPanel sliders=new JPanel();
      sliders.setLayout(new BoxLayout(sliders,BoxLayout.Y_AXIS));
      sliders.add(outputs[ci]);
      sliders.add(thresholdRenderers[ci]);
      sliders.add(minSliders[ci]);
      sliders.add(maxSliders[ci]);
      JLabel minLabel=new JLabel(df.format(minVal));
      minLabel.setForeground(Color.RED);
      JLabel maxLabel=new JLabel(df.format(maxVal));
      maxLabel.setForeground(Color.BLUE);
      JPanel labels=new JPanel();
      labels.setLayout(new GridLayout(2,3));
      labels.add(new JLabel("min"));
      labels.add(new JLabel("val"));
      labels.add(new JLabel("max"));
      labels.add(minLabel);
      labels.add(currentValLabels[ci]);
      labels.add(maxLabel);
      minSliders[ci].addChangeListener(new ChangeListener(){
        @Override public void stateChanged(        ChangeEvent e){
          float val=(((JSlider)e.getSource()).getValue() - 200f) / 100f;
          minLabel.setText(df.format(val));
          fwdTask.setMinVal(ci,val);
          config.setPref(Config.osc_settings,String.valueOf(Config.osc_settings_params.address) + ci + "min","" + val);
          config.store();
          thresholdRenderers[ci].setMin(val);
        }
      }
);
      maxSliders[ci].addChangeListener(new ChangeListener(){
        @Override public void stateChanged(        ChangeEvent e){
          float val=(((JSlider)e.getSource()).getValue() - 200f) / 100f;
          maxLabel.setText(df.format(val));
          fwdTask.setMaxVal(ci,val);
          config.setPref(Config.osc_settings,String.valueOf(Config.osc_settings_params.address) + ci + "max","" + val);
          config.store();
          thresholdRenderers[ci].setMax(val);
        }
      }
);
      sliderPanel.add(labels,BorderLayout.NORTH);
      sliderPanel.add(sliders,BorderLayout.CENTER);
      outputs[ci].getDocument().addDocumentListener(new DocumentListener(){
        public void save(){
          String string=outputs[ci].getText();
          if (config != null) {
            config.setPref(Config.osc_settings,String.valueOf(Config.osc_settings_params.address) + ci,string);
            config.store();
          }
          if (string.length() > 0) {
            fwdTask.setOutputString(ci,string);
          }
        }
        @Override public void changedUpdate(        DocumentEvent arg0){
          save();
        }
        @Override public void insertUpdate(        DocumentEvent arg0){
          save();
        }
        @Override public void removeUpdate(        DocumentEvent arg0){
          save();
        }
      }
);
      settingsPanel.add(sliderPanel);
    }
  }
  this.add(settingsPanel,BorderLayout.CENTER);
  this.setSize(oscWidth,oscHeight);
  this.setVisible(true);
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
