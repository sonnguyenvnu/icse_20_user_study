package Algorithm.Algorithms_4thEdition.c_Graph;

import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * 图的表示，本书使用邻接表数组（adjoin）
 */
@Getter
public class Graph {

    private int edgeCount;              //边的数目
    private Map<String, Set<String>> adjoinMap;   //邻接表（使用Set 为了�?��?平行边）

    /**
     * 从文件�?始化图
     * 文件格�?：
     * vertexCount  第一个数字：顶点的数目
     * edgeCount    第二个数字：边的数目
     * x    y       接下�?�的没两个点组�?��?一�?�边
     * x    z
     *
     * @param filePath
     * @throws IOException
     */
    public Graph(Path filePath) throws IOException {
        InputStream inputStream = Files.newInputStream(filePath);
        Scanner scanner = new Scanner(new BufferedInputStream(inputStream));
        this.init(scanner.nextInt());
        scanner.nextLine();
        for (int i = 0; i < this.edgeCount; i++) {
            //将�?个顶点所对应的边，放入邻接表数组中
            String[] split = scanner.nextLine().split(" ");
            String x = split[0];
            String y = split[1];
            addEdge(x, y);
            addEdge(y, x);  //无�?�图
        }
    }

    private void addEdge(String x, String y) {
        Set<String> adjoinVertexSet = adjoinMap.get(x);
        if (adjoinVertexSet != null) {
            adjoinVertexSet.add(y);
            adjoinMap.put(x, adjoinVertexSet);
        } else {
            adjoinVertexSet = new HashSet<>();
            adjoinVertexSet.add(y);
            adjoinMap.put(x, adjoinVertexSet);
        }
    }

    /**
     * �?始化边和顶点，以�?�邻接Set表
     *
     * @param edgeCount
     */
    private void init(int edgeCount) {
        this.edgeCount = edgeCount;
        this.adjoinMap = new HashMap<>();
    }

}
