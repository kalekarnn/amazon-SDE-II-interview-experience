import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class Amazon {
    public static void main(String[] args) {
        List<Integer> files = new ArrayList<>();
        files.add(8);
        files.add(4);
        files.add(6);
        files.add(12);
        System.out.println(minimumTime(3, files));

        List<Integer> row1 = new ArrayList<>();
        row1.add(1);
        row1.add(0);
        row1.add(0);

        List<Integer> row2 = new ArrayList<>();
        row2.add(1);
        row2.add(0);
        row2.add(0);

        List<Integer> row3 = new ArrayList<>();
        row3.add(1);
        row3.add(9);
        row3.add(1);

        List<List<Integer>> area = new ArrayList<>();
        area.add(row1);
        area.add(row2);
        area.add(row3);

        System.out.println(minimumDistance(3, 3, area));
    }

    static int minimumTime(int numOfSubFiles, List<Integer> files) {
        // WRITE YOUR CODE HERE
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.addAll(files);

        int totalTime = 0;
        while (priorityQueue.size() > 1) {
            int temp = priorityQueue.poll() + priorityQueue.poll();
            priorityQueue.add(temp);
            totalTime += temp;
        }
        return totalTime;
    }

    static int minimumDistance(int numRows, int numColumns, List<List<Integer>> area) {
        // WRITE YOUR CODE HERE
        int[][] areamatix = new int[numRows][numColumns];
        for (int i = 0; i < numRows; i++) {
            List<Integer> integerList = area.get(i);
            for (int j = 0; j < numColumns; j++) {
                areamatix[i][j] = integerList.get(j);
            }
        }

        int distance = 0;
        Stack<String> stack = new Stack<>();
        stack.push("0,0");
        while(true){

            String[] pair = stack.pop().split(",");
            int rowIndex = Integer.parseInt(pair[0]);
            int colIndex = Integer.parseInt(pair[1]);
            if(areamatix[rowIndex][colIndex] == 1){
                areamatix[rowIndex][colIndex] = 2; //visited
                distance++;

                if(colIndex-1>0){
                    stack.push(rowIndex+","+(colIndex-1));//left
                }
                if(colIndex+1<numColumns){
                    stack.push(rowIndex+","+(colIndex+1));//right
                }
                if(rowIndex-1 > 0){
                    stack.push(rowIndex-1+","+colIndex); //up
                }
                if(rowIndex+1<numRows){
                    stack.push(rowIndex+1+","+colIndex); //down
                }
            }
            else if(areamatix[rowIndex][colIndex] == 9){
                return distance;
            }
            else {
                if(stack.isEmpty())
                    return -1;
                else
                    continue;
            }
        }
    }
}
