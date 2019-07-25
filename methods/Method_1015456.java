public void start() throws Exception {
  mainFrame=new JFrame("Chat demo");
  mainFrame.setPreferredSize(new Dimension(600,600));
  mainFrame.setBackground(Color.white);
  mainFrame.addWindowListener(new WindowAdapter(){
    public void windowClosing(    WindowEvent e){
      stomp_client.send(USER_LEFT,USER,username);
      stomp_client.send(CLIENT_LEFT,CLIENT,username);
      stomp_client.disconnect();
      System.exit(0);
    }
  }
);
  connect();
  Box main_box=Box.createVerticalBox();
  main_box.setBackground(Color.white);
  Box input=Box.createHorizontalBox();
  Box buttons=Box.createHorizontalBox();
  mainFrame.add(main_box);
  main_box.add(Box.createVerticalStrut(10));
  main_box.add(cluster);
  cluster.setAlignmentX(Component.LEFT_ALIGNMENT);
  main_box.add(Box.createVerticalStrut(10));
  main_box.add(Box.createVerticalStrut(10));
  main_box.add(users_label);
  main_box.add(Box.createVerticalStrut(10));
  txtArea=new TextArea();
  txtArea.setPreferredSize(new Dimension(550,500));
  txtArea.setEditable(false);
  txtArea.setBackground(Color.white);
  main_box.add(txtArea);
  main_box.add(Box.createVerticalStrut(10));
  main_box.add(input);
  main_box.add(Box.createVerticalStrut(10));
  main_box.add(buttons);
  csLabel.setPreferredSize(new Dimension(85,30));
  input.add(csLabel);
  txtField=new JTextField();
  txtField.setPreferredSize(new Dimension(200,30));
  txtField.setBackground(Color.white);
  input.add(txtField);
  leaveButton=new JButton("Leave");
  leaveButton.setPreferredSize(new Dimension(150,30));
  buttons.add(leaveButton);
  leaveButton.addMouseListener(new MouseAdapter(){
    public void mouseClicked(    MouseEvent e){
      stomp_client.send(USER_LEFT,USER,username);
      stomp_client.send(CLIENT_LEFT,CLIENT,username);
      stomp_client.disconnect();
      System.exit(0);
    }
  }
);
  sendButton=new JButton("Send");
  sendButton.setPreferredSize(new Dimension(150,30));
  buttons.add(sendButton);
  sendButton.addMouseListener(new MouseAdapter(){
    public void mouseClicked(    MouseEvent e){
      send(txtField.getText());
      txtField.selectAll();
    }
  }
);
  clearButton=new JButton("Clear");
  clearButton.setPreferredSize(new Dimension(150,30));
  clearButton.addMouseListener(new MouseAdapter(){
    public void mouseClicked(    MouseEvent e){
      txtArea.setText("");
    }
  }
);
  buttons.add(clearButton);
  status.setForeground(Color.red);
  main_box.add(status);
  mainFrame.pack();
  mainFrame.setLocation(15,25);
  Dimension main_frame_size=mainFrame.getSize();
  txtArea.setPreferredSize(new Dimension((int)(main_frame_size.width * 0.9),(int)(main_frame_size.height * 0.8)));
  mainFrame.setVisible(true);
  txtField.setFocusable(true);
  txtField.requestFocusInWindow();
  txtField.setToolTipText("type and then press enter to send");
  txtField.addActionListener(e -> {
    String cmd=e.getActionCommand();
    if (cmd != null && !cmd.isEmpty()) {
      send(txtField.getText());
      txtField.selectAll();
    }
  }
);
  sendGetUsers();
}
