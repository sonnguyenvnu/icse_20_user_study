/*
 * Copyright 2003-2013 JetBrains s.r.o.
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
package jetbrains.mps.lang.pattern.util;

import jetbrains.mps.lang.smodel.generator.smodelAdapter.AttributeOperations;
import jetbrains.mps.logging.Logger;
import jetbrains.mps.util.EqualUtil;
import jetbrains.mps.util.IterableUtil;
import org.apache.log4j.LogManager;
import org.jetbrains.mps.openapi.language.SContainmentLink;
import org.jetbrains.mps.openapi.language.SDataType;
import org.jetbrains.mps.openapi.language.SProperty;
import org.jetbrains.mps.openapi.language.SReferenceLink;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SReference;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

// FIXME seems to be the only class in patternRuntime to use [kernel], not [openapi]. Perhaps, worth moving into [kernel]?
public class MatchingUtil {
  private static final Logger LOG = Logger.wrap(LogManager.getLogger(MatchingUtil.class));

  public static boolean matchNodes(SNode node1, SNode node2) {
    return matchNodes(node1, node2, IMatchModifier.DEFAULT, true);
  }

  public static boolean matchNodes(SNode node1, SNode node2, IMatchModifier matchModifier, boolean matchAttributes) {
    if (node1 == node2) return true;
    if (node1 == null) return false;
    if (node2 == null) return false;
    if (!node1.getConcept().equals(node2.getConcept())) return false;

    //properties
    final Set<SProperty> properties = new HashSet<>();
    properties.addAll(IterableUtil.asSet(node1.getProperties()));
    properties.addAll(IterableUtil.asSet(node2.getProperties()));

    for (SProperty property : properties) {
      // use of SNode.getProperty() directly (not SNodeAccessUtil.getProperty())
      // as we are checking for structural equality. If there's e.g. a 'derived' property
      // == getName() + getNodeId(), its values from SNodeAccessUtil would differ and nodes would not match
      // (assuming NodeId is different and nodes otherwise match).
      String stringValue1 = node1.getProperty(property);
      String stringValue2 = node2.getProperty(property);
      Object propertyValue1 = stringValue1;
      Object propertyValue2 = stringValue2;
      if (!IterableUtil.asCollection(node1.getConcept().getProperties()).contains(property)) {
        SNode diagnosticsNode = stringValue1 != null ? node1 : node2;
        LOG.warning("can't find a property declaration for property `" + property.getName() + "` in a concept " + diagnosticsNode.getConcept().getQualifiedName(), diagnosticsNode);
        LOG.warning("try to compare just properties' internal values");
      } else {
        SDataType dataType = property.getType();
        if (dataType != null) {
          propertyValue1 = dataType.fromString(stringValue1);
          propertyValue2 = dataType.fromString(stringValue2);
        }
      }
      if (!EqualUtil.equals(propertyValue1, propertyValue2)) return false;
    }

    //-- matching references
    Set<SReferenceLink> referenceRoles = new HashSet<>();
    for (SReference ref : node1.getReferences()) {
      referenceRoles.add(ref.getLink());
    }
    for (SReference ref : node2.getReferences()) {
      referenceRoles.add(ref.getLink());
    }
    for (SReferenceLink role : referenceRoles) {
      SNode target1 = node1.getReferenceTarget(role);
      SNode target2 = node2.getReferenceTarget(role);
      if (matchModifier.accept(target1, target2)) {
        matchModifier.performAction(target1, target2);
        continue;
      }
      if (target2 != target1) return false;
    }

    // children
    Set<SContainmentLink> childRoles = jetbrains.mps.util.SNodeOperations.getChildRoles(node1, matchAttributes);
    childRoles.addAll(jetbrains.mps.util.SNodeOperations.getChildRoles(node2, matchAttributes));
    for (SContainmentLink role : childRoles) {
      List<SNode> children1 = IterableUtil.asList(node1.getChildren(role));
      List<SNode> children2 = IterableUtil.asList(node2.getChildren(role));
      if (matchModifier.acceptList(children1, children2)) {
        matchModifier.performGroupAction(children1, children2);
        continue;
      }
      Iterator<? extends SNode> childrenIterator = children1.iterator();
      for (SNode child2 : children2.toArray(new SNode[0])) {
        SNode child1 = childrenIterator.hasNext() ? childrenIterator.next() : null;
        if (matchModifier.accept(child1, child2)) {
          matchModifier.performAction(child1, child2);
          continue;
        }
        if (!matchNodes(child1, child2, matchModifier, matchAttributes)) return false;
      }
      while (childrenIterator.hasNext()) {
        SNode child1 = childrenIterator.next();
        SNode child2 = null;
        if (matchModifier.accept(child1, child2)) {
          matchModifier.performAction(child1, child2);
          continue;
        }
        if (!matchNodes(child1, child2, matchModifier, matchAttributes)) return false;
      }
    }

    return true;
  }

  public static int hash(SNode node) {
    int result = node.getConcept().hashCode();
    for (SReference reference : node.getReferences()) {
      SNode targetNode = jetbrains.mps.util.SNodeOperations.getTargetNodeSilently(reference);
      if (targetNode != null) {
        result = 31 * result + reference.getLink().hashCode();
        result = 31 * result + targetNode.hashCode();
      }
    }
    Map<String, String> properties = jetbrains.mps.util.SNodeOperations.getProperties(node);
    for (String propertyName : properties.keySet()) {
      result = 31 * result + propertyName.hashCode();
    }
    for (String propertyValue : properties.values()) {
      result = 31 * result + propertyValue.hashCode();
    }
    for (SNode child : node.getChildren()) {
      if (AttributeOperations.isAttribute(child)) continue;
      result = 31 * result + child.getContainmentLink().hashCode();
      result = 31 * result + hash(child);
    }
    return result;
  }
}
