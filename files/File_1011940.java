/*
 * Copyright 2003-2018 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jetbrains.mps.excluded;

import jetbrains.mps.core.platform.Platform;
import jetbrains.mps.util.JDOMUtil;
import jetbrains.mps.util.containers.MultiMap;
import jetbrains.mps.vfs.IFile;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class GensourcesModuleFile {
  // gensources.iml constants
  public static final String MODULE_ROOT_MANAGER = "NewModuleRootManager";
  public static final String CONTENT = "content";
  public static final String URL = "url";
  /**
   * IDEA Module can declare path relative to MODULE_DIR only (i.e. no PROJECT_DIR). gensources.iml resides under tools/gensources/, hence ../../ to get
   * to project root
   */
  public static final String PATH_START_MODULE = "file://$MODULE_DIR$/../../";
  public static final String SOURCE_FOLDER = "sourceFolder";
  public static final String EXCLUDE_FOLDER = "excludeFolder";

  private final Platform myPlatform;
  private final File myGensourcesIml;
  private final Document myResult;
  // initially blank; is populated with newly created CONTENT elements
  private final Element myRootManagerElement;
  // read
  private Set<String> myRegularModuleSources;
  // built
  private final Set<String> myGeneratedModuleSources = new HashSet<>();
  // read
  private Set<String> myRegularModuleContentRoots;
  // built
  private final Set<String> myGeneratedModuleContentRoots = new HashSet<>();

  public GensourcesModuleFile(Platform mpsPlatform, File genSourcesIml) throws JDOMException, IOException  {
    myPlatform = mpsPlatform;
    myGensourcesIml = genSourcesIml;
    myResult = JDOMUtil.loadDocument(genSourcesIml);
    myRootManagerElement = new Element(MODULE_ROOT_MANAGER);
  }

  public void prepare() throws JDOMException, IOException {
    collectSourcesOfRegularModules();
  }

  private void collectSourcesOfRegularModules() throws JDOMException, IOException {
    Set<String> modelRoots = new HashSet<>();
    Set<String> sourcesIncluded = new HashSet<>();
    for (File imlFile : Utils.withExtension(".iml", Utils.files(new File(".")))) {
      if (imlFile.getCanonicalPath().equals(myGensourcesIml.getCanonicalPath())) continue;
      Document doc = JDOMUtil.loadDocument(imlFile);
      Element rootManager = Utils.getComponentWithName(doc, MODULE_ROOT_MANAGER);
      for (Element cRoot : rootManager.getChildren(CONTENT)) {
        String imlFormattedRoot = cRoot.getAttributeValue(URL);
        modelRoots.add(new File(imlFormattedRoot.replace("file://$MODULE_DIR$", imlFile.getParent())).getCanonicalPath());
        for (Element sFolder : cRoot.getChildren(SOURCE_FOLDER)) {
          String imlFormattedSourceRoot = sFolder.getAttributeValue(URL);
          sourcesIncluded.add(new File(imlFormattedSourceRoot.replace("file://$MODULE_DIR$", imlFile.getParent())).getCanonicalPath());
        }
      }
    }
    myRegularModuleSources = sourcesIncluded;
    myRegularModuleContentRoots = modelRoots;
  }

  public void updateGenSourcesIml(File... sourceDirs) throws IOException {
    for (File dir : sourceDirs) {
      Element contentRoot = new Element(CONTENT);
      contentRoot.setAttribute(URL, PATH_START_MODULE + dir);
      myGeneratedModuleContentRoots.add(dir.getCanonicalPath());
      myRootManagerElement.addContent(contentRoot);

      // generate lists of source gen and classes gen folders and add as source and excluded to content root
      List<String> sourceGenFolders = new ArrayList<>();
      List<String> classesGenFolders = new ArrayList<>();
      collectGeneratedSourcesAndClassesDirs(sourceGenFolders, classesGenFolders, dir);
      myGeneratedModuleSources.addAll(sourceGenFolders);

      for (String sourceGenFolder : sourceGenFolders) {
        Element sourceFolder = new Element(SOURCE_FOLDER);
        sourceFolder.setAttribute(URL, PATH_START_MODULE + Utils.getRelativeProjectPath(sourceGenFolder));
        sourceFolder.setAttribute("isTestSource", "false");
        contentRoot.addContent(sourceFolder);
      }
      for (String classesGenFolder : classesGenFolders) {
        Element excludeFolder = new Element(EXCLUDE_FOLDER);
        excludeFolder.setAttribute(URL, PATH_START_MODULE + Utils.getRelativeProjectPath(classesGenFolder));
        contentRoot.addContent(excludeFolder);
      }
    }
  }

  public void serializeResult() throws IOException {
    ArrayList<Element> contentElements = new ArrayList<>(myRootManagerElement.getChildren(CONTENT));
    myRootManagerElement.removeContent();
    // it looks IDEA sorts content roots according to their URL value, do the same to avoid content roots jumping back and forth
    Collections.sort(contentElements, (o1, o2) -> o1.getAttributeValue(URL).compareTo(o2.getAttributeValue(URL)));
    final Element rootManager = Utils.getComponentWithName(myResult, MODULE_ROOT_MANAGER);
    // remove content roots, we re-create them from scratch
    int contentStart = rootManager.indexOf(rootManager.getChild(CONTENT));
    rootManager.removeChildren(CONTENT);
    rootManager.addContent(contentStart, contentElements);

    JDOMUtil.writeDocument(myResult, myGensourcesIml);
  }

  // param sourceGen and classesGen are filled with absolute paths of existing folders with generated source/classes of MPS modules discovered under specified location
  private void collectGeneratedSourcesAndClassesDirs(List<String> sourceGen, List<String> classesGen, File... startFrom) throws IOException {
    for (File dir : startFrom) {
      MPSModuleCollector moduleCollector = new MPSModuleCollector(myPlatform);
      moduleCollector.collect(dir);
      for (DescriptorEntry module : moduleCollector.getOutcome()) {
        for (String sourcePath : module.getSourcePaths()) {
          if (myRegularModuleSources.contains(sourcePath)) {
            continue;
          }
          String sourceCanonical = new File(sourcePath).getCanonicalPath();
          assert sourceCanonical.startsWith(dir.getCanonicalPath()) : "module generates files to outside of 'root' folder for it:\n" + module.getModuleDir() + "\ngenerates into\n" + sourcePath;
          if (new File(sourcePath).exists()) {
            sourceGen.add(sourcePath);
          }
        }
        for (IFile classes : module.getClassGenPaths()) {
          // FIXME IFile.getPath() always gives path with unix slashes, while MPSModuleCollector has platform-dependent paths in getSourcePaths (due to the odd
          // way paths are represented in module descriptor, which we shall address anyway (i.e. replace strings with IFile or Path)).
          // To make sourceGen and classesGen lists consistent, I transform to platform paths here, although the better fix would be to collect IFile for sourcePath
          // and to match IFile instead of string in Utils.getRelativeProjectPath. See https://youtrack.jetbrains.com/issue/MPS-27673.
          File localFile = new File(classes.getPath());
          if (localFile.exists()) {
            classesGen.add(localFile.getCanonicalPath());
          }
        }
      }
    }
    Collections.sort(sourceGen);
    Collections.sort(classesGen);
  }

  // afaiu, the difference between this method and updateGenSourcesIml, above, is that this one follows some hideous logic to group
  // discovered locations under deduced content roots.
  public void updateGenSourcesImlNoIntersections(File... sourceDirs) throws IOException {
    Set<String> modelRoots = new HashSet<>(myRegularModuleContentRoots);
    modelRoots.addAll(myGeneratedModuleContentRoots);
    List<String> sourceGen = new ArrayList<>();
    List<String> classesGen = new ArrayList<>();
    collectGeneratedSourcesAndClassesDirs(sourceGen, classesGen, sourceDirs);
    sourceGen.removeAll(myRegularModuleSources);
    sourceGen.removeAll(myGeneratedModuleSources);

    Set<String> newRoots = new HashSet<>();
    for (String sGen : sourceGen) {
      String root = null;

      // find existing
      for (String newRoot : newRoots) {
        if (sGen.equals(newRoot) || sGen.startsWith(newRoot + File.separator)) {
          root = newRoot;
        }
      }

      //find outermost directory not intersecting with other model roots
      if (root == null) {
        root = sGen;
        String parent = new File(root).getParent();
        while (!intersects(modelRoots, parent)) {
          root = parent;
          parent = new File(root).getParent();
        }
        newRoots.add(root);

        Element contentRoot = new Element(CONTENT);
        contentRoot.setAttribute(URL, PATH_START_MODULE + Utils.getRelativeProjectPath(root));
        myRootManagerElement.addContent(contentRoot);
      }

      String rootInImlFormat = PATH_START_MODULE + Utils.getRelativeProjectPath(root);
      Element contentRoot = Utils.getChildByAttribute(myRootManagerElement, CONTENT, URL, rootInImlFormat);
      assert contentRoot != null : "Root: "+root+"; iml formatted: " + rootInImlFormat + "; source folder: " + sGen;

      Element sourceFolder = new Element(SOURCE_FOLDER);
      sourceFolder.setAttribute(URL, PATH_START_MODULE + Utils.getRelativeProjectPath(sGen));
      sourceFolder.setAttribute("isTestSource", "false");
      contentRoot.addContent(sourceFolder);
    }

    for (String cGen : classesGen) {
      String root = null;
      for (String newRoot : newRoots) {
        if (cGen.equals(newRoot) || cGen.startsWith(newRoot + File.separator)) {
          root = newRoot;
        }
      }

      //assert root != null : "Classes gen folder which has no corresponding content root: " + cGen;
      if (root == null) continue;

      String rootInImlFormat = PATH_START_MODULE + Utils.getRelativeProjectPath(root);
      Element contentRoot = Utils.getChildByAttribute(myRootManagerElement, CONTENT, URL, rootInImlFormat);

      Element excludeFolder = new Element(EXCLUDE_FOLDER);
      excludeFolder.setAttribute(URL, PATH_START_MODULE + Utils.getRelativeProjectPath(cGen));
      contentRoot.addContent(excludeFolder);
    }
  }

  public static MultiMap<String, String> getSourceFolders(File root) throws JDOMException, IOException {
    MultiMap<String, String> sourcesIncluded = new MultiMap<>();
    for (File imlFile : Utils.withExtension(".iml", Utils.files(root))) {
      Document doc = JDOMUtil.loadDocument(imlFile);
      Element rootManager = Utils.getComponentWithName(doc, MODULE_ROOT_MANAGER);
      for (Element cRoot : rootManager.getChildren(CONTENT)) {
        for (Element sFolder : cRoot.getChildren(SOURCE_FOLDER)) {
          String imlFormattedRoot = sFolder.getAttributeValue(URL);
          String sourcePath = new File(imlFormattedRoot.replace("file://$MODULE_DIR$", imlFile.getParent())).getCanonicalPath();
          sourcesIncluded.putValue(imlFile.getCanonicalPath(), sourcePath);
        }
      }
    }
    return sourcesIncluded;
  }

  private static boolean intersects(Set<String> existingRoots, String parent) {
    for (String root : existingRoots) {
      if (root.equals(parent) || root.startsWith(parent + File.separator)) return true;
    }
    return false;
  }
}
