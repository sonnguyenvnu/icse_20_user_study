/*
 * Copyright 2003-2015 JetBrains s.r.o.
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

/**
 * IHolder generic enough to hold any object. It's up to IFinder implementation to deal with actual value.
 * @author Artem Tikhomirov
 */
public class GenericHolder<T> implements IHolder<T> {
  private final T myValue;
  private final String myCaption;

  public GenericHolder(@NotNull T value) {
    this(value, value.toString());
  }

  public GenericHolder(@NotNull T value, @NotNull String caption) {
    myValue = value;
    myCaption = caption;
  }

  @Override
  public T getObject() {
    return myValue;
  }

  @NotNull
  @Override
  public String getCaption() {
    return myCaption;
  }

  @Override
  public void read(Element element, Project project) throws CantLoadSomethingException {
    throw new CantLoadSomethingException("No idea how an object might be serialized");
  }

  @Override
  public void write(Element element, Project project) throws CantSaveSomethingException {
    throw new CantSaveSomethingException("No idea how an object might be serialized");
  }
}
