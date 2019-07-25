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
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.persistence.PersistenceFacade;

public class ModelHolder implements IHolder<SModelReference> {
  private static final String MODEL = "model";
  private static final String UID = "uid";

  public SModelReference myModelReference;

  public ModelHolder(Element element, Project project) throws CantLoadSomethingException {
    read(element, project);
  }

  public ModelHolder(SModelReference modelReference) {
    myModelReference = modelReference;
  }

  @Override
  public SModelReference getObject() {
    return myModelReference;
  }

  @Override
  @NotNull
  public String getCaption() {
    return myModelReference.getName().getSimpleName();
  }

  @Override
  public void write(Element element, Project project) throws CantSaveSomethingException {
    Element modelXML = new Element(MODEL);
    modelXML.setAttribute(UID, myModelReference.toString());
    element.addContent(modelXML);
  }

  @Override
  public void read(Element element, Project project) throws CantLoadSomethingException {
    Element modelXML = element.getChild(MODEL);
    try {
      myModelReference = PersistenceFacade.getInstance().createModelReference(modelXML.getAttributeValue(UID));
    } catch (IllegalArgumentException ex) {
      throw new CantLoadSomethingException("cannot parse model reference", ex);
    }
  }
}
