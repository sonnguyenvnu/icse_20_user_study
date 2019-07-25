/*
 * Copyright 2003-2016 JetBrains s.r.o.
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
package jetbrains.mps.ide.findusages.model.holders;

import jetbrains.mps.ide.findusages.CantLoadSomethingException;
import jetbrains.mps.ide.findusages.CantSaveSomethingException;
import jetbrains.mps.project.Project;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeReference;
import org.jetbrains.mps.openapi.persistence.PersistenceFacade;

public class NodeHolder implements IHolder<SNodeReference> {
  private static final String NODE = "node";
  private static final String REF = "ref";
  private static final String CAPTION = "title";

  private SNodeReference myNodePointer;
  private String myTitle; // can be null

  public NodeHolder(Element element, Project project) throws CantLoadSomethingException {
    read(element, project);
  }

  public NodeHolder(@NotNull SNode node) {
    myNodePointer = node.getReference();
    myTitle = node.getPresentation();
  }

  @Override
  public SNodeReference getObject() {
    return myNodePointer;
  }

  @Override
  @NotNull
  public String getCaption() {
    return myTitle == null ? myNodePointer.toString() : myTitle;
  }

  @Override
  public void read(Element element, Project project) throws CantLoadSomethingException {
    Element nodeXML = element.getChild(NODE);
    if (nodeXML == null || nodeXML.getAttribute(REF) == null) {
      throw new CantLoadSomethingException("node is null");
    }

    myNodePointer = PersistenceFacade.getInstance().createNodeReference(nodeXML.getAttributeValue(REF));
    myTitle = nodeXML.getAttributeValue(CAPTION);
  }

  @Override
  public void write(Element element, Project project) throws CantSaveSomethingException {
    Element nodeXML = new Element(NODE);
    nodeXML.setAttribute(REF, PersistenceFacade.getInstance().asString(myNodePointer));
    if (myTitle != null) {
      nodeXML.setAttribute(CAPTION, myTitle);
    }
    element.addContent(nodeXML);
  }
}
