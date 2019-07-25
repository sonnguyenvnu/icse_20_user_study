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
package jetbrains.mps.workbench.choose;

import javax.swing.Icon;

/**
 * {@linkplain ElementPresentation#render(Object, ElementDescriptor) Bridge} between element in {@link ChooseByNameData chooser list element} and its UI presentation.
 * @author Artem Tikhomirov
 * @since 3.4
 */
public final class ElementDescriptor {
  public String name;
  public String location;
  public Icon icon;

  public void reset() {
    name = location = null;
    icon = null;
  }
}
