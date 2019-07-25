/*
 * Copyright 2003-2019 JetBrains s.r.o.
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
package jetbrains.mps.ide.findusages.view.treeholder.tree.nodedatatypes;

import jetbrains.mps.ide.findusages.CantLoadSomethingException;
import jetbrains.mps.ide.findusages.CantSaveSomethingException;
import jetbrains.mps.ide.findusages.IExternalizeable;
import jetbrains.mps.ide.findusages.view.treeholder.tree.TextOptions;
import jetbrains.mps.ide.findusages.view.treeholder.treeview.path.PathItemRole;
import jetbrains.mps.project.Project;
import jetbrains.mps.util.annotation.ToRemove;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.Icon;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public abstract class BaseNodeData implements IExternalizeable {
  private static final String CAPTION = "caption";
  private static final String INFO = "info";
  private static final String EXCLUDED = "excluded";
  private static final String RESULTS_SECTION = "results_section";
  private static final String ISRESULT = "isresult";
  private static final String ROLE = "role";

  private PathItemRole myRole;
  private String myCaption;
  private String myAdditionalInfo;
  private boolean myResultsSection;
  private boolean myIsExcluded;
  private boolean myIsPathTail;

  private BaseNodeData myHead, myTail;
  private BaseNodeData myNext;

  protected BaseNodeData() {

  }

  public BaseNodeData(Element element, Project project) throws CantLoadSomethingException {
    read(element, project);
  }

  public BaseNodeData(PathItemRole role, @NotNull String caption, @Nullable String additionalInfo, boolean isPathTail, boolean resultsSection) {
    myRole = role;
    myCaption = caption;
    myAdditionalInfo = additionalInfo;
    myResultsSection = resultsSection;
    myIsExcluded = false;
    myIsPathTail = isPathTail;
  }

  //----MAIN DATA STUFF----

  //must be used only via DataTree

  public void setExcluded(boolean isExcluded) {
    myIsExcluded = isExcluded;
  }

  public boolean isResultsSection() {
    return myResultsSection;
  }

  public boolean isExcluded() {
    return myIsExcluded;
  }

  public PathItemRole getRole() {
    return myRole;
  }

  public String getCaption() {
    return myCaption;
  }

  /**
   * @deprecated use {@link #getCaption()} and {@link #getAdditionalInfo()} instead
   */
  @ToRemove(version = 2019.2)
  @Deprecated
  public String getText(TextOptions options) {
    return getCaption();
  }

  @Nullable
  public String getAdditionalInfo() {
    return myAdditionalInfo;
  }

  /**
   * @deprecated use {@link #getCaption()}  instead. there are no longer html tags in the values
   */
  @Deprecated
  @ToRemove(version = 2019.2)
  public String getPlainText() {
    return myCaption.replaceAll("<[^>]*>", "");
  }

  public boolean isInvalid() {
    return getIdObject() == null;
  }

  public boolean isPathTail() {
    return myIsPathTail;
  }

  public boolean isResultNode() {
    return myIsPathTail && myResultsSection;
  }

  public void setIsPathTail_internal(boolean isResult) {
    myIsPathTail = isResult;
  }

  // adds a child to the tail of children list of this node; child expected to have no own children.
  public void addChild(BaseNodeData child) {
    assert child != null;
    assert child.myNext == null; // myTail would need to be updated otherwise
    if (myHead == null) {
      myHead = myTail = child;
    } else {
      myTail.myNext = child;
      myTail = child;
    }
  }

  public Stream<BaseNodeData> children() {
    if (myHead == null) {
      return Stream.empty();
    }
    return StreamSupport.stream(Spliterators.spliteratorUnknownSize(new Iterator<BaseNodeData>() {
      private BaseNodeData myCursor = myHead;
      @Override
      public boolean hasNext() {
        return myCursor != null;
      }

      @Override
      public BaseNodeData next() {
        BaseNodeData rv= myCursor;
        myCursor = myCursor.myNext;
        return rv;
      }
    }, 0), false);
  }

  public boolean hasChildren() {
    return myHead != null;
  }

  //----SAVE/LOAD STUFF----

  @Override
  public void write(Element element, Project project) throws CantSaveSomethingException {
    element.setAttribute(CAPTION, myCaption);
    if (myAdditionalInfo != null) {
      element.setAttribute(INFO, myAdditionalInfo);
    }
    element.setAttribute(EXCLUDED, Boolean.toString(myIsExcluded));
    element.setAttribute(ISRESULT, Boolean.toString(myIsPathTail));
    element.setAttribute(RESULTS_SECTION, Boolean.toString(myResultsSection));

    Element roleXML = new Element(ROLE);
    PathItemRole.write(myRole, roleXML);
    element.addContent(roleXML);

    for (BaseNodeData ch = myHead; ch != null; ch = ch.myNext) {
      Element childElement = new Element("child");
      Element ce = new Element("instance");
      ce.setAttribute("qcn", ch.getClass().getName());
      childElement.addContent(ce);
      ch.write(childElement, project);
      element.addContent(childElement);
    }
  }

  @Override
  public void read(Element element, Project project) throws CantLoadSomethingException {
    myCaption = element.getAttributeValue(CAPTION);
    myAdditionalInfo = element.getAttributeValue(INFO);
    myIsExcluded = Boolean.parseBoolean(element.getAttributeValue(EXCLUDED));
    myIsPathTail = Boolean.parseBoolean(element.getAttributeValue(ISRESULT));
    myResultsSection = Boolean.parseBoolean(element.getAttributeValue(RESULTS_SECTION));

    Element roleXML = element.getChild(ROLE);
    myRole = PathItemRole.read(roleXML);
    //
    for (Element childElement : element.getChildren("child")) {
      final String nodeClassName = childElement.getChild("instance").getAttributeValue("qcn");
      try {
        Class<?> cls = Class.forName(nodeClassName);
        BaseNodeData ch = (BaseNodeData) cls.getConstructor(Element.class, Project.class).newInstance(childElement, project);
        addChild(ch);
      } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
        if (e instanceof  InvocationTargetException && e.getCause() instanceof CantLoadSomethingException) {
          throw (CantLoadSomethingException) e.getCause();
        }
        throw new CantLoadSomethingException("can't instantiate node " + nodeClassName, e);
      }
    }
  }

  //----CONCRETE DATA TYPE STUFF----

  public abstract Icon getIcon(PresentationContext presentationContext);

  public abstract Object getIdObject();
}
