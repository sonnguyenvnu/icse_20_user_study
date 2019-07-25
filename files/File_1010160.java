/*
 * Copyright 2003-2014 JetBrains s.r.o.
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
package jetbrains.mps.generator.impl.plan;

import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SNodeUtil;
import org.jetbrains.mps.openapi.model.SReference;
import jetbrains.mps.util.GraphUtil;
import org.jetbrains.mps.openapi.model.SNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Uses linear algorithms.
 * <p/>
 * Evgeny Gryaznov, Jan 11, 2010
 */
public class ConnectedComponentPartitioner {
  private static Component[] EMPTY_COMPONENTS = new Component[0];

  private int myCount;
  private SNode[] myRoots;
  private int[][] myDependencies;

  public ConnectedComponentPartitioner(List<SNode> roots) {
    myCount = roots.size();
    myRoots = roots.toArray(new SNode[this.myCount]);
    myDependencies = buildDependencies();
  }

  private int[][] buildDependencies() {
    int[] dependsOn = new int[myCount];
    int[][] result = new int[myCount][];

    Map<SNode, Integer> rootIndex = new HashMap<>();
    for (int i = 0; i < myRoots.length; i++) {
      rootIndex.put(myRoots[i], i);
    }

    for (int index = 0; index < myCount; index++) {
      SNode root = myRoots[index];
      for (SNode node : SNodeUtil.getDescendants(root, null, true)) {
        buildNodeDependencies(node, dependsOn, rootIndex);
      }
      dependsOn[index] = 0;
      result[index] = GraphUtil.setToList(dependsOn);
    }
    return result;
  }

  private void buildNodeDependencies(SNode node, int[] dependsOn, Map<SNode, Integer> rootIndex) {
    for (SReference ref : node.getReferences()) {
      SModel sm = node.getModel();
      assert sm != null;
      if (!sm.getReference().equals(ref.getTargetSModelReference())) continue;
      SNode targetNode = ref.getTargetNode();
      if (targetNode != null) {
        Integer targetIndex = rootIndex.get(targetNode.getContainingRoot());
        if (targetIndex != null) {
          dependsOn[targetIndex] = 1;
        }
      }
    }
  }

  public List<SNode[]> partition() {
    int[][] partitions = GraphUtil.components(GraphUtil.removeOrientation(myDependencies));

    List<SNode[]> result = new ArrayList<>(partitions.length + 1);
    for (int[] partition : partitions) {
      SNode[] proots = new SNode[partition.length];
      for (int e = 0; e < proots.length; e++) {
        proots[e] = myRoots[partition[e]];
      }
      result.add(proots);
    }
    result.add(new SNode[0]);
    return result;
  }

  public Component[] partitionStrong() {
    int[][] partitions = GraphUtil.tarjan(myDependencies);

    // temporary
    byte[] dependencies = new byte[partitions.length];
    int[] rootToComponent = new int[myRoots.length];
    Arrays.fill(rootToComponent, -1);

    Component[] result = new Component[partitions.length];
    for (int i = 0; i < partitions.length; i++) {

      // create roots array
      SNode[] roots = new SNode[partitions[i].length];
      for (int e = 0; e < roots.length; e++) {
        int rootIndex = partitions[i][e];
        roots[e] = myRoots[rootIndex];
        rootToComponent[rootIndex] = i;
      }

      // calc dependencies
      int count = 0;
      Arrays.fill(dependencies, (byte) 0);
      for (int e = 0; e < roots.length; e++) {
        int rootIndex = partitions[i][e];
        for (int dependsOn : myDependencies[rootIndex]) {
          int componentIndex = rootToComponent[dependsOn];
          assert componentIndex >= 0;
          if (componentIndex < i && dependencies[componentIndex] == 0) {
            dependencies[componentIndex] = 1;
            count++;
          }
        }
      }

      Component[] dep = count > 0 ? new Component[count] : EMPTY_COMPONENTS;
      for (int e = 0; count > 0 && e < i; e++) {
        if (dependencies[e] == 1) {
          dep[--count] = result[e];
        }
      }

      result[i] = new Component(roots, dep);
    }

    return result;
  }

  @Override
  public String toString() {
    int[][] strongPartitions = GraphUtil.tarjan(myDependencies);
    int[][] partitions = GraphUtil.components(GraphUtil.removeOrientation(myDependencies));

    return printPartitions(partitions) + "\n\nStrong:\n" + printPartitions(strongPartitions);
  }

  private String printPartitions(int[][] partitions) {
    StringBuffer sb = new StringBuffer();
    sb.append(myRoots.length).append(" roots, ").append(partitions.length).append(" components\n");
    for (int i = 0; i < partitions.length; i++) {
      sb.append('#').append(i).append('(').append(partitions[i].length).append("): ");
      for (int e = 0; e < partitions[i].length; e++) {
        sb.append(' ').append(myRoots[partitions[i][e]]);
      }
      sb.append('\n');
    }
    return sb.toString();
  }

  public static class Component {
    private final SNode[] roots;
    private final Component[] dependsOn;
    private boolean isDirty;

    public Component(SNode[] roots, Component[] dependsOn) {
      this.roots = roots;
      this.dependsOn = dependsOn;
    }

    public SNode[] getRoots() {
      return roots;
    }

    public Component[] getDependsOn() {
      return dependsOn;
    }

    public boolean isDirty() {
      return isDirty;
    }

    public void setDirty(boolean dirty) {
      isDirty = dirty;
    }
  }
}
