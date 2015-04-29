import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;
import java.io.*;
import java.util.*;

public class TSPNearestNeighbour
{
    private int numberOfNodes;
    private Stack<Integer> stack;
 
    public TSPNearestNeighbour()
    {
        stack = new Stack<Integer>();
    }
 
    public ArrayList tsp(int adjacencyMatrix[][], int startNode)
    {
        numberOfNodes = adjacencyMatrix[1].length - 1;
        int[] visited = new int[numberOfNodes + 1];
        visited[startNode] = 1;
        stack.push(startNode);
        //visited[1] = 1;
        //stack.push(1);
        int element, dst = 0, i;
        int min = Integer.MAX_VALUE;
        boolean minFlag = false;
        ArrayList nodeList = new ArrayList();
        nodeList.add(startNode);
 
        while (!stack.isEmpty())
        {
            element = stack.peek();
            i = 1;
            min = Integer.MAX_VALUE;
            while (i <= numberOfNodes)
            {
                if (adjacencyMatrix[element][i] > 1 && visited[i] == 0)
                {
                    if (min > adjacencyMatrix[element][i])
                    {
                        min = adjacencyMatrix[element][i];
                        dst = i;
                        minFlag = true;
                    }
                }
                i++;
            }
            if (minFlag)
            {
                visited[dst] = 1;
                stack.push(dst);
                nodeList.add(dst);
                //System.out.print(dst + "\t");
                minFlag = false;
                continue;
            }
            stack.pop();
        }

        return nodeList;
    }
 
    public static void main(String... arg) throws IOException
    {
        int number_of_nodes;
        try
        {
            int T = 1; // number of test cases
            Scanner fin = new Scanner (new File (arg[0]));
            int N = fin.nextInt();
            //System.out.println(N);
            int[][] d = new int[N + 1][N + 1];
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    d[i][j] = fin.nextInt();
                    //System.out.print(d[i][j] + " ");
                }
                //System.out.println();
            }

            //System.out.println("the citys are visited as follows");
            TSPNearestNeighbour tspNearestNeighbour = new TSPNearestNeighbour();
            
            ArrayList<Integer> diffCosts = new ArrayList<Integer>();
            fin.close();
            for (int start = 1; start <= N; start++) {
                ArrayList nodeList = tspNearestNeighbour.tsp(d, start);
                int cost = 0;
                for (int i = 0; i < nodeList.size(); i++) {
                    // System.out.print(nodeList.get(i) + " ");
                }
                for (int i = 0; i < nodeList.size() - 1; i++) {
                    cost += (int)d[(int)nodeList.get(i)][(int)nodeList.get(i+1)];

                }
                // System.out.println();
                // System.out.println("Cost: " + cost);
                diffCosts.add(cost);

            }
            // calculate average
            double average = 0;
            for (int i = 0; i < diffCosts.size(); i++) {
                average += diffCosts.get(i);
            }
            average = average / diffCosts.size();
            System.out.println("Average: " + average);

            double sd = 0;
            for (int i=0; i<diffCosts.size();i++)
            {
                sd = sd + Math.pow(diffCosts.get(i) - average, 2);
            }
            sd = sd / diffCosts.size();
            sd = Math.sqrt(sd);
            System.out.println("STD: " + sd);
        } catch (InputMismatchException inputMismatch)
         {
             System.out.println("Wrong Input format");
         }
    }
}