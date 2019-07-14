private void initComponents(final boolean standalone){
  imageFolderHelpLabel=new JLabel();
  imageFolderField=new JTextField();
  chooseImageFolderButton=new JButton();
  soundFileHelpLabel=new JLabel();
  soundFileField=new JTextField();
  chooseSoundFileButton=new JButton();
  createMovieButton=new JButton();
  widthLabel=new JLabel();
  widthField=new JTextField();
  heightLabel=new JLabel();
  heightField=new JTextField();
  compressionLabel=new JLabel();
  compressionBox=new JComboBox<String>();
  fpsLabel=new JLabel();
  fpsField=new JTextField();
  originalSizeCheckBox=new JCheckBox();
  setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
  addWindowListener(new WindowAdapter(){
    public void windowClosing(    WindowEvent e){
      setVisible(false);
    }
  }
);
  registerWindowCloseKeys(getRootPane(),new ActionListener(){
    public void actionPerformed(    ActionEvent actionEvent){
      if (standalone) {
        System.exit(0);
      }
 else {
        setVisible(false);
      }
    }
  }
);
  setTitle(Language.text("movie_maker.title"));
  aboutLabel=new JLabel(Language.text("movie_maker.blurb"));
  imageFolderHelpLabel.setText(Language.text("movie_maker.image_folder_help_label"));
  chooseImageFolderButton.setText(Language.text("movie_maker.choose_button"));
  chooseImageFolderButton.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      Chooser.selectFolder(MovieMaker.this,Language.text("movie_maker.select_image_folder"),new File(imageFolderField.getText()),new Chooser.Callback(){
        void select(        File file){
          if (file != null) {
            imageFolderField.setText(file.getAbsolutePath());
          }
        }
      }
);
    }
  }
);
  soundFileHelpLabel.setText(Language.text("movie_maker.sound_file_help_label"));
  chooseSoundFileButton.setText(Language.text("movie_maker.choose_button"));
  chooseSoundFileButton.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      Chooser.selectInput(MovieMaker.this,Language.text("movie_maker.select_sound_file"),new File(soundFileField.getText()),new Chooser.Callback(){
        void select(        File file){
          if (file != null) {
            soundFileField.setText(file.getAbsolutePath());
          }
        }
      }
);
    }
  }
);
  createMovieButton.setText(Language.text("movie_maker.create_movie_button"));
  createMovieButton.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      String lastPath=prefs.get("movie.outputFile",null);
      File lastFile=lastPath == null ? null : new File(lastPath);
      Chooser.selectOutput(MovieMaker.this,Language.text("movie_maker.save_dialog_prompt"),lastFile,new Chooser.Callback(){
        @Override void select(        File file){
          if (file != null) {
            String path=file.getAbsolutePath();
            if (!path.toLowerCase().endsWith(".mov")) {
              path+=".mov";
            }
            prefs.put("movie.outputFile",path);
            createMovie(new File(path));
          }
        }
      }
);
    }
  }
);
  Font font=new Font("Dialog",Font.PLAIN,11);
  widthLabel.setFont(font);
  widthLabel.setText(Language.text("movie_maker.width"));
  widthField.setColumns(4);
  widthField.setFont(font);
  widthField.setText("320");
  heightLabel.setFont(font);
  heightLabel.setText(Language.text("movie_maker.height"));
  heightField.setColumns(4);
  heightField.setFont(font);
  heightField.setText("240");
  compressionLabel.setFont(font);
  compressionLabel.setText(Language.text("movie_maker.compression"));
  compressionBox.setFont(font);
  compressionBox.setModel(new DefaultComboBoxModel<String>(new String[]{Language.text("movie_maker.compression.animation"),Language.text("movie_maker.compression.jpeg"),Language.text("movie_maker.compression.png")}));
  fpsLabel.setFont(font);
  fpsLabel.setText(Language.text("movie_maker.framerate"));
  fpsField.setColumns(4);
  fpsField.setFont(font);
  fpsField.setText("30");
  originalSizeCheckBox.setFont(font);
  originalSizeCheckBox.setText(Language.text("movie_maker.orig_size_button"));
  originalSizeCheckBox.setToolTipText(Language.text("movie_maker.orig_size_tooltip"));
  GroupLayout layout=new GroupLayout(getContentPane());
  getContentPane().setLayout(layout);
  layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(61,61,61).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(widthLabel).addComponent(fpsLabel)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(fpsField,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(compressionLabel).addGap(1,1,1).addComponent(compressionBox,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(originalSizeCheckBox)).addGroup(layout.createSequentialGroup().addComponent(widthField,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(heightLabel).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(heightField,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))).addGap(41,41,41)).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(aboutLabel,GroupLayout.DEFAULT_SIZE,484,Short.MAX_VALUE).addComponent(imageFolderHelpLabel).addComponent(soundFileHelpLabel).addGroup(layout.createSequentialGroup().addComponent(soundFileField,GroupLayout.DEFAULT_SIZE,372,Short.MAX_VALUE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(chooseSoundFileButton)).addComponent(createMovieButton,GroupLayout.Alignment.TRAILING).addGroup(GroupLayout.Alignment.TRAILING,layout.createSequentialGroup().addComponent(imageFolderField,GroupLayout.DEFAULT_SIZE,372,Short.MAX_VALUE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(chooseImageFolderButton)))).addGroup(layout.createSequentialGroup().addContainerGap()))));
  layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(aboutLabel,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE).addGap(18,18,18).addComponent(imageFolderHelpLabel).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(imageFolderField,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE).addComponent(chooseImageFolderButton)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(widthLabel).addComponent(widthField,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE).addComponent(heightLabel).addComponent(heightField,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(compressionBox,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE).addComponent(fpsLabel).addComponent(fpsField,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE).addComponent(compressionLabel).addComponent(originalSizeCheckBox)).addGap(18,18,18).addComponent(soundFileHelpLabel).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(soundFileField,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE).addComponent(chooseSoundFileButton)).addGap(18,18,18).addComponent(createMovieButton).addContainerGap()));
  pack();
}
