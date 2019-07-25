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
import org.jetbrains.mps.openapi.module.SModuleReference;
import org.jetbrains.mps.openapi.persistence.PersistenceFacade;

/**
 * Replacement for {@code jetbrains.mps.ide.findusages.model.holders.ModuleHolder} that doesn't force SModule resolution
 * @author Artem Tikhomirov
 */
public class ModuleRefHolder implements IHolder<SModuleReference> {
  private static final String UID = "moduleReference";
  private SModuleReference myReference;

  public ModuleRefHolder(Element element, Project project) throws CantLoadSomethingException {
    read(element, project);
  }

  public ModuleRefHolder(@NotNull SModuleReference moduleReference) {
    myReference = moduleReference;
  }

  @Override
  public SModuleReference getObject() {
    return myReference;
  }

  @NotNull
  @Override
  public String getCaption() {
    return myReference.getModuleName();
  }

  @Override
  public void read(Element element, Project project) throws CantLoadSomethingException {
    try {
      myReference = PersistenceFacade.getInstance().createModuleReference(element.getAttributeValue(UID));
    } catch (Exception ex) {
      throw new CantLoadSomethingException(ex);
    }
  }

  @Override
  public void write(Element element, Project project) throws CantSaveSomethingException {
    element.setAttribute(UID, PersistenceFacade.getInstance().asString(myReference));
  }
}
